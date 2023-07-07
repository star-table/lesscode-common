/**
 * 
 */
package com.polaris.lesscode.feign;

import java.util.Map;
import java.util.function.Supplier;

import org.springframework.util.CollectionUtils;

import com.polaris.lesscode.consts.CommonConsts;
import com.polaris.lesscode.exception.SystemException;
import com.polaris.lesscode.util.ApplicationContextUtil;
import com.polaris.lesscode.vo.BaseResultCode;

import lombok.extern.slf4j.Slf4j;

/**
 * @author admin
 *
 */
@Slf4j
public abstract class AbstractBaseFallback {

	/**
	 * global config key.
	 */
	private static final String GLOBLE_KEY = "all";

	/**
	 * default deal process.
	 * 
	 * @param <T>
	 * @param supplier
	 * @return
	 */
	protected <T> T wrappDeal(String appName, Throwable cause, Supplier<T> supplier) {
		log.error(appName + "服务发生异常", cause);
		if (isMockedFlg(appName)) {
			log.info("return a mocked data");
			return supplier.get();
		}
		throw new SystemException(BaseResultCode.FALLBACK_ERROR, cause);
	}

	private boolean isMockedFlg(String appName) {
		Map<String, Boolean> mockedFlgMap = ApplicationContextUtil.getBeanObjectByName(CommonConsts.FALLBACK_MOCKED_MAP_BEAN_NAME);
		if (CollectionUtils.isEmpty(mockedFlgMap)) {
			return false;
		}
		// 全局挡板打开
		if (Boolean.TRUE.equals(mockedFlgMap.get(GLOBLE_KEY))) {
			return true;
		}
		// 判断单个服务的挡板是否打开
		if (Boolean.TRUE.equals(mockedFlgMap.get(appName))) {
			return true;
		}
		return false;
	}
}
