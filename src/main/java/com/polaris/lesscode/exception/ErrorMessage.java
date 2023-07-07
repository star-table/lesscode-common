package com.polaris.lesscode.exception;


import com.polaris.lesscode.vo.AbstractResultCode;

public class ErrorMessage {
    private AbstractResultCode error;
    private Object[] msgs;

    public ErrorMessage(AbstractResultCode errorCode) {
        this.error = errorCode;
    }

    public ErrorMessage(AbstractResultCode errorCode, Object... msgs) {
        this.error = errorCode;

        this.msgs = msgs;
    }

    public AbstractResultCode getError() {
        return error;
    }

    public Object[] getMsgs() {
        return msgs;
    }

    @Override
    public String toString() {
        return String.format(error.getMessage(), msgs);
    }
}
