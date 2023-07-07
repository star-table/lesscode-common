package com.polaris.lesscode.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.polaris.lesscode.context.RequestContext;
import com.polaris.lesscode.util.DateFormatUtil;
import com.polaris.lesscode.util.JsonUtils;
import com.polaris.lesscode.util.StreamUtils;

public class RequestLoggerFilter implements Filter{

	private static final Logger logger = LoggerFactory.getLogger(RequestLoggerFilter.class);
    private static final Logger rrlogger = LoggerFactory.getLogger("RR");
	
	private static final String REQUEST_PREFIX = "request|";
	private static final String RESPONSE_PREFIX = "response|";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		LocalDateTime start = LocalDateTime.now();
		try {
			request = new RequestWrapper((HttpServletRequest) request);
			response = new ResponseWrapper((HttpServletResponse) response);
			chain.doFilter(request, response);
		}finally {
			LocalDateTime end = LocalDateTime.now();
			rrLogger((HttpServletRequest) request, (ResponseWrapper)response, start,end);
		}
	}

	protected void rrLogger(final HttpServletRequest request, final ResponseWrapper response, LocalDateTime startTime,LocalDateTime endTime){
		StringBuilder msg = new StringBuilder();

		if(StringUtils.isBlank(response.getContentType()) || !response.getContentType().startsWith("application/json")) {
			return;
		}
		
		msg.append(REQUEST_PREFIX);
		msg.append("_traceId=").append(RequestContext.getTraceId()).append("|");
		msg.append("start=").append(DateFormatUtil.parseDateToStr(startTime.toInstant(ZoneOffset.of("+8")), DateFormatUtil.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS)).append("|");
		if (request.getContentType() != null) {
			msg.append("content_type=").append(request.getContentType()).append("|");
		}else{
			msg.append("content_type=|");
		}
		msg.append("uri=").append(request.getRequestURI());
		if (request.getQueryString() != null) {
			msg.append('?').append(request.getQueryString());
		}

		if (request instanceof RequestWrapper && !isMultipart(request) && !isBinaryContent(request)) {
			RequestWrapper requestWrapper = (RequestWrapper) request;
			try {
				String charEncoding = requestWrapper.getCharacterEncoding() != null ?
						requestWrapper.getCharacterEncoding() : "UTF-8";
						String body = StreamUtils.getStringByStream(requestWrapper.getInputStream(), charEncoding);
						msg.append("|body=").append(body);
						if("POST".equals(requestWrapper.getMethod().toUpperCase()) && StringUtils.isEmpty(body)){
							msg.append(JsonUtils.toJson(request.getParameterMap()));
						}
			} catch (IOException e) {
				logger.error("_traceId=" + RequestContext.getTraceId() + "Failed to parse request payload", e);
			}
		}else{
			msg.append("|body=");
		}

		msg.append("]----[").append(RESPONSE_PREFIX);
		msg.append("_traceId=").append(RequestContext.getTraceId()).append("|");
		msg.append("end=").append(DateFormatUtil.parseDateToStr(endTime.toInstant(ZoneOffset.of("+8")), DateFormatUtil.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS)).append("|");
		msg.append("time=").append(Duration.between(startTime,endTime).toMillis()).append("|");
		msg.append("status=").append(response.getStatus());
		byte[] responseBytes = response.toByteArray();
		if (responseBytes.length <= 1024 * 1024 * 10){
			msg.append("|body=").append(new String(responseBytes));
		}



		rrlogger.info(msg.toString());
	}


	private boolean isBinaryContent(final HttpServletRequest request) {
		if (request.getContentType() == null) {
			return false;
		}
		return request.getContentType().startsWith("image") || request.getContentType().startsWith("video")
				|| request.getContentType().startsWith("audio");
	}


	private boolean isMultipart(final HttpServletRequest request) {
		return request.getContentType() != null && request.getContentType().startsWith("multipart/form-data");
	}
}
