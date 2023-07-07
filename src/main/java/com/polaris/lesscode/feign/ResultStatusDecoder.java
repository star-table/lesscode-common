/**
 * 
 */
package com.polaris.lesscode.feign;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.IOUtils;

import com.google.gson.JsonObject;
import com.polaris.lesscode.consts.CommonConsts;
import com.polaris.lesscode.exception.BusinessException;
import com.polaris.lesscode.exception.SystemException;
import com.polaris.lesscode.util.GsonUtils;
import com.polaris.lesscode.vo.BaseResultCode;


import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Bomb.
 *
 */
@Slf4j
public class ResultStatusDecoder implements Decoder {
	
	/**
	 * system error code Sets
	 */
	private static Set<Integer> sysErrorCodeSets = new HashSet<Integer>();
	
	/**
	 * delegate 
	 */
	private final Decoder delegate;
	
	/**
	 * static collected the inner system error code.
	 * provided by common exception handler.
	 */
	static {
		sysErrorCodeSets.add(BaseResultCode.PARAM_ERROR.getCode());
		sysErrorCodeSets.add(BaseResultCode.PATH_NOT_FOUND.getCode());
		sysErrorCodeSets.add(BaseResultCode.SYS_ERROR_MSG.getCode());
		sysErrorCodeSets.add(BaseResultCode.INTERNAL_SERVER_ERROR.getCode());
	}
	
	/**
	 * default constructor with a feign decoder.
	 * @param delegate Decoder
	 */
	public ResultStatusDecoder(Decoder delegate) {
		this.delegate = delegate;
	}

	@Override
	public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {		
		//fetch openfeign returned json string result.
        String resultStr = IOUtils.toString(response.body().asInputStream(), StandardCharsets.UTF_8);
        try {
        	JsonObject jo = GsonUtils.toJsonNode(resultStr);
			if (jo.get("code") != null) {
				int rCode = jo.get("code").getAsInt();
				String msgStr = jo.get("message").getAsString();
				if(sysErrorCodeSets.contains(rCode)) {
					log.info(resultStr);
					throw new SystemException(rCode, msgStr);
				}
				if(!(BaseResultCode.OK.equals(rCode) || CommonConsts.SC_OK_200.equals(rCode))) {
					log.info(resultStr);
					throw new BusinessException(rCode, msgStr);
				}
			}
        }catch(SystemException e) {
        	throw new SystemException(BaseResultCode.INTERNAL_SERVER_ERROR, e);
        }
        // rewrite the response.
        return delegate.decode(response.toBuilder().body(resultStr, StandardCharsets.UTF_8).build(), type);
	}

}
