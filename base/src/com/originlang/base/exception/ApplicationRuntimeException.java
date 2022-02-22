package com.originlang.base.exception;

import java.io.Serial;


public class ApplicationRuntimeException extends RuntimeException {


    @Serial
    private static final long serialVersionUID = 7241951976545676429L;
    protected int errCode = -100000;
    protected String errMsg = "app rt exception";

    public int getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public ApplicationRuntimeException(String message, Throwable cause) {
        super(message, cause);
        this.errMsg = message;
    }

    public ApplicationRuntimeException(int errCode, String message, Throwable cause) {
        super(message, cause);
        this.errCode = errCode;
        this.errMsg = message;
    }

    public ApplicationRuntimeException(String message) {
        super(message);
        this.errMsg = message;
    }

    public ApplicationRuntimeException(int errCode, String message) {
        super(message);
        this.errCode = errCode;
        this.errMsg = message;
    }

    public ApplicationRuntimeException(Throwable cause) {
        super(cause);
    }

    public ApplicationRuntimeException(int errCode, Throwable cause) {
        super(cause);
        this.errCode = errCode;
    }


}
