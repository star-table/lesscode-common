package com.polaris.lesscode.exception;


import com.polaris.lesscode.vo.AbstractResultCode;
import com.polaris.lesscode.vo.BaseResultCode;

@Deprecated
public class SysErrorException extends RuntimeException {
    private int code = -1;

    private AbstractResultCode resultCode;
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public SysErrorException(String message) {
        super(message);
    }

    public SysErrorException(AbstractResultCode error, Object... args) {
        super(String.format(error.getMessage(), args));
        this.resultCode = error;
        this.code = error.getCode();
    }

    public int getCode() {
        return this.code;
    }

    public AbstractResultCode getResultCodeInfo() {
        return this.resultCode;
    }
}
