package com.polaris.lesscode.consts;

public interface RequestContextConsts {

	/**
	 * 传递userId的header
	 */
	String IDENTITY_USER_HEADER = "X-POLARIS-IDENTITY-USER";
	/**
	 * 传递orgId的header
	 */
	String IDENTITY_ORG_HEADER = "X-POLARIS-IDENTITY-ORG";
	
	/**
	 * traceId
	 */
	String TRACE_ID = "X-POLARIS-TRACE-ID";
}
