package com.polaris.lesscode.vo;

@Deprecated
public enum ResultCode implements AbstractResultCode{
	OK(0,"OK"),
	TOKEN_ERROR(401,"token错误"),
	FORBIDDEN_ACCESS(403,     "无权访问"),
	PATH_NOT_FOUND(404,   "请求地址不存在"),
	PARAM_ERROR(501,"请求参数错误"),
	INTERNAL_SERVER_ERROR(500, "服务器异常"),
	SYS_ERROR_MSG(996,  "系统异常, %s "),
	FAILURE(997,  "业务失败"),
	SYS_ERROR(998,    "系统异常"),
	UNKNOWN_ERROR(999,  "未知错误"),
	SOCKET_TIMEOUT_ERROR(1000,"网络超时请稍后再试"),
	UPDATA_SUCCESS(200,   "更新成功"),
	UPDATA_FAIL(100010,"更新失败"),
	INTERNAL_SERVICE_ERROR(150000,  "内部服务异常"),

	APP_ADD_FAIL(400001, "应用添加失败"),
	APP_MODIFY_FAIL(400002,    "应用更新失败"),
	APP_DELETE_FAIL(400003,    "应用删除失败"),
	APP_NOT_EXIST(400004,  "应用不存在"),
	APP_DISABLE(400005,"应用不可用"),
	APP_VALUE_ADD_FAIL(400006,       "应用数据添加失败"),
	APP_VALUE_NOT_EXIST(400007,        "应用数据不存在"),
	APP_VALUE_MODIFY_FAIL(400008, "应用数据更新失败"),
	APP_VALUE_DELETE_FAIL(400009, "应用数据删除失败"),
	APP_FORM_CONFIG_IS_EMPTY_ERROR(400010,       "应用表单配置为空"),
	APP_PACKAGE_ADD_FAIL(400011,"应用包添加失败"),
	APP_PACKAGE_MODIFY_FAIL(400012,"应用包更新失败"),
	APP_PACKAGE_NOT_EXIST(400013, "应用包不存在"),
	APP_PACKAGE_GROUP_NOT_EXIST(400014,    "应用包组不存在"),
	APP_PACKAGE_GROUP_ADD_FAIL(400015,   "应用包组添加失败"),
	APP_PACKAGE_GROUP_MODIFY_FAIL(400016,      "应用包组更新失败"),
	APP_FORM_NOT_EXIST(400017,       "应用表单不存在"),
	APP_GROUP_ADD_FAIL(400018,       "应用组添加失败"),
	APP_GROUP_MODIFY_FAIL(400019, "应用组更新失败"),
	APP_GROUP_NOT_EXIST(4000020,        "应用组不存在"),
	APP_PACKAGE_RELATION_ADD_FAIL(4000021,      "应用包关联添加失败"),
	APP_PACKAGE_RELATION_DEL_FAIL(4000022,      "应用包关联删除失败"),
	APP_VALUE_ADD_CHECK_FAIL(4000023, "应用数据添加校验失败"),
	APP_FORM_ADD_FAIL(400024,       "应用表单添加失败"),
	APP_FORM_MODIFY_FAIL(400025,       "应用表单修改失败"),

	//Form
	FORM_LIST_FILTER_ERROR(410001,  "表单列表过滤错误"),
	FORM_VALUE_REQUIRED(410002,        "表单字段值必填"),
	FORM_VALUE_LT_MINWORDCOUNT(410003,   "表单字段值少于最少字数"),
	FORM_VALUE_MT_MAXWORDCOUNT(410004,   "表单字段值多于最多字数"),
	FORM_VALUE_NOT_FORMATDATE(410005,  "表单字段值不符合日期格式"),
	FORM_VALUE_NOT_FORMATNUM(410006, "表单字段值不符合数字类型格式"),
	FORM_VALUE_NOT_DECIMAL(410007,  "表单字段值不允许是小数"),
	FORM_VALUE_MT_DECIMALDIGIT(410008,   "表单字段值超过限制位数"),
	FORM_VALUE_LT_MINVALUE(410009,  "表单字段值小于最小值"),
	FORM_VALUE_MT_MAXVALUE(410010,  "表单字段值大于最大值"),

	FORM_OP_NO_BATCH_PRINT(420001,"无批量打印权限"),
	FORM_OP_NO_BATCH_UPDATE(420002,"无批量修改权限"),
	FORM_OP_NO_COPY(420003,"无复制权限"),
	FORM_OP_NO_CREATE(420004,"无添加权限"),
	FORM_OP_NO_DELETE(420005,"无删除权限"),
	FORM_OP_NO_EXPORT(420006,"无导出权限"),
	FORM_OP_NO_IMPORT(420007,"无导入权限"),
	FORM_OP_NO_READ(420008,"无查看权限"),
	FORM_OP_NO_UPDATE(420009,"无编辑权限"),

	//Robot
	ROBOT_ADD_FAIL(500001,   "Robot添加失败"),
	ROBOT_NOT_EXIST(500002,    "Robot不存在"),
	ROBOT_CONFIG_MODIFY_FAIL(500003, "Robot配置更新失败"),
	ROBOT_EMAIL_MODIFY_FAIL(500004,"Robot Email更新失败"),
	ROBOT_SMS_MODIFY_FAIL(500005, "Robot SMS更新失败"),
	ROBOT_DEL(500006,   "该Robot已删除"),
	SELECT_NULL(500007,"未查到数据"),
	ROBOT_DELETE_FAIL(500008,      "Robot删除失败"),

	SOURCE_EDIT_FAIL(600001,     "Source修改失败"),
	SOURCE_DELETE_FAIL(600002,       "Source删除失败"),
	LINKER_EDIT_FAIL(600003,     "Linker修改失败"),
	LINKER_DELETE_FAIL(600004,       "Linker删除失败"),

	//User Center
	ORG_INVALID(800001,"组织无效"),

	//Data Center
	NO_AVAILABLE_DATASOURCE(700001,"没有可用的数据源"),
	NO_AVAILABLE_DATABASE(700002, "没有可用的数据库"),
	CURRENT_DATASOURCE_DISABLE(700003,   "当前数据源不可用，请联系客服"),
	CURRENT_DATABASE_DISABLE(700004, "当前数据库不可用，请联系客服"),
	STAGE_NOT_EXIST(700005,    "阶段不存在"),
	DATA_QUERY_ERROR(700006,     "数据查询异常"),

	//Aggregation
	AGGREGATION_TABLE_NOT_EXIST(900001,    "聚合表不存在"),
	AGGREGATION_ADD_FAIL(900002,"聚合表添加失败"),
	AGGREGATION_UPDATE_FAIL(900003,"聚合表更新失败"),

	//User Center
	TOKEN_AUTH_FIAL(300301,    "身份认证异常，请重新登录"),
	;


	private ResultCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	private int code;

	private String message;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static ResultCode parse(int code) {
		for (ResultCode rc : values()) {
			if (rc.getCode() == code) {
				return rc;
			}
		}
		return ResultCode.SYS_ERROR;
	}

	public boolean equals(Integer code) {
		return Integer.valueOf(this.getCode()).equals(code);
	}


}
