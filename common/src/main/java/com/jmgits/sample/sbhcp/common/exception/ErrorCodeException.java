package com.jmgits.sample.sbhcp.common.exception;

/**
 * Created by javi.more.garc on 08/01/17.
 */
public class ErrorCodeException extends RuntimeException {

    private final ErrorCode code;

    public ErrorCodeException(ErrorCode code) {
        this.code = code;
    }

    public ErrorCodeException(ErrorCode code, String message) {
        super(message);
        this.code = code;
    }

    public ErrorCodeException(ErrorCode code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public ErrorCodeException(ErrorCode code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public ErrorCode getCode() {
        return code;
    }
}
