package com.polaris.lesscode.context;

import lombok.Data;

@Data
public class RequestContextInfo {

	private Long userId;
	
	private Long orgId;
	
	private String traceId;
}
