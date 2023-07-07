package com.polaris.lesscode.filter;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.spi.http.HttpContext;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import com.polaris.lesscode.consts.RequestContextConsts;
import com.polaris.lesscode.context.RequestContext;
import com.polaris.lesscode.context.RequestContextInfo;

import feign.Request;

public class RequestContextFilter implements Filter{
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		doIt((HttpServletRequest) request, (HttpServletResponse) response, chain);
	}

	protected void doIt(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			String orgId = request.getHeader(RequestContextConsts.IDENTITY_ORG_HEADER);
			String userId = request.getHeader(RequestContextConsts.IDENTITY_USER_HEADER);
			
			RequestContextInfo requestContextInfo = new RequestContextInfo();
			RequestContext.setRequstContextInfo(requestContextInfo);
			
			//assembly data
			if(isId(orgId) && isId(userId)) {
				requestContextInfo.setOrgId(Long.valueOf(orgId));
				requestContextInfo.setUserId(Long.valueOf(userId));	
			}
			
			String traceId = request.getHeader(RequestContextConsts.TRACE_ID);
			if(StringUtils.isBlank(traceId)) {
				traceId = UUID.randomUUID().toString().replaceAll("-", "");
			}
			MDC.put("traceId", traceId);
			response.setHeader(RequestContextConsts.TRACE_ID, traceId);
			RequestContext.setTraceId(traceId);
			
			chain.doFilter(request, response);	
		}finally {
			RequestContext.clear();
			MDC.clear();
		}
	}
	
	private boolean isId(String id) {
		return StringUtils.isNotBlank(id) && StringUtils.isNumeric(id);
	}

}
