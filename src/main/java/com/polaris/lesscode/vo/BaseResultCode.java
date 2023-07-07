package com.polaris.lesscode.vo;

public enum BaseResultCode implements AbstractResultCode{
    OK(0, "OK"),
    TOKEN_ERROR(401, "token错误"),
    FORBIDDEN_ACCESS(403, "无权访问"),
    PATH_NOT_FOUND(404, "请求地址不存在"),
    PARAM_ERROR(501, "请求参数错误"),
    INTERNAL_SERVER_ERROR(500, "服务器异常"),
    FALLBACK_ERROR(995,"服务异常"),
    SYS_ERROR_MSG(996, "系统异常, %s "),
    FAILURE(997, "业务失败"),
    SYS_ERROR(998, "系统异常"),
    UNKNOWN_ERROR(999, "未知错误"),
    SOCKET_TIMEOUT_ERROR(1000, "网络超时请稍后再试"),
    UPDATA_SUCCESS(200, "更新成功"),
    UPDATA_FAIL(100010, "更新失败"),
    RATE_LIMITER_OVERFLOW_ERROR(913,"请求流速超限"),
    INTERNAL_SERVICE_ERROR(150000, "内部服务异常"),
    ;

    private BaseResultCode(int code, String message) {
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

    public static BaseResultCode parse(int code) {
        for (BaseResultCode rc : values()) {
            if (rc.getCode() == code) {
                return rc;
            }
        }
        return BaseResultCode.SYS_ERROR;
    }

    public boolean equals(Integer code) {
        return Integer.valueOf(this.getCode()).equals(code);
    }


}
