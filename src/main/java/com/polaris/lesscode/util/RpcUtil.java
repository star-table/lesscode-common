package com.polaris.lesscode.util;

import com.polaris.lesscode.exception.SysErrorException;
import com.polaris.lesscode.vo.Result;
import com.polaris.lesscode.vo.BaseResultCode;

@Deprecated
public class RpcUtil {

	public static <T> T accept(Result<T> result) {
		if(result == null) {
			throw new SysErrorException(BaseResultCode.INTERNAL_SERVICE_ERROR);
		}
		if(! result.isSuccess()) {
			throw new SysErrorException(BaseResultCode.parse(result.getCode()));
		}
		return result.getData();
	}
}
