package com.polaris.lesscode.enums;

public enum ApplicationEnum {
	
	USERCENTER("usercenter", "用户中心服务"),
	APP("app", "应用服务"),
	FORM("form", "表单服务"),
	DATACENTER("datacenter", "oltp数据中心服务"),
	WORKFLOW("workflow", "工作流服务"),
	AGG("agg", "聚合表"),
	DASHBOARD("dashboard", "仪表盘"),
	;
	
	private String code;
	
	private String desc;

	private ApplicationEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
