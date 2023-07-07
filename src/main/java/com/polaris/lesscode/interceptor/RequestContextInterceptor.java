package com.polaris.lesscode.interceptor;

import org.slf4j.MDC;

import com.polaris.lesscode.consts.RequestContextConsts;
import com.polaris.lesscode.context.RequestContext;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class RequestContextInterceptor implements RequestInterceptor{

	@Override
	public void apply(RequestTemplate requestTemplate) {
		if(RequestContext.currentUserId() != null && RequestContext.currentOrgId() != null) {
			requestTemplate.header(RequestContextConsts.IDENTITY_ORG_HEADER, String.valueOf(RequestContext.currentOrgId()));
			requestTemplate.header(RequestContextConsts.IDENTITY_USER_HEADER, String.valueOf(RequestContext.currentUserId()));
		}
		requestTemplate.header(RequestContextConsts.TRACE_ID, RequestContext.getTraceId());
	}

}
