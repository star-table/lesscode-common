package com.polaris.lesscode.context;

public class RequestContext {

	private static final InheritableThreadLocal<RequestContextInfo> threadLocal = new InheritableThreadLocal<>();
	
	public static RequestContextInfo getRequestContextInfo() {
		return threadLocal.get();
	}
	
	public static void setRequstContextInfo(RequestContextInfo info) {
		threadLocal.set(info);
	}

	public static Long currentUserId() {
		RequestContextInfo requestContextInfo = getRequestContextInfo();
		return requestContextInfo != null ? requestContextInfo.getUserId() : null;
	}
	
	public static Long currentOrgId() {
		RequestContextInfo requestContextInfo = getRequestContextInfo();
		return requestContextInfo != null ? requestContextInfo.getOrgId() : null;
	}
	
	public static String getTraceId() {
		RequestContextInfo requestContextInfo = getRequestContextInfo();
		return requestContextInfo != null ? requestContextInfo.getTraceId() : "";
	}
	
	public static void setTraceId(String traceId) {
		RequestContextInfo requestContextInfo = getRequestContextInfo();
		if(requestContextInfo != null) {
			requestContextInfo.setTraceId(traceId);
		}
	}
	
	public static void clear() {
		threadLocal.remove();
	}
}
