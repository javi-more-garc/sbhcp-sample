package com.jmgits.sample.sbhcp.common.exception;

/**
 * Created by javi.more.garc on 08/01/17.
 */
public class JsonException extends RuntimeException {

    public JsonException() {
        // default constructor
    }

    public JsonException(String message) {
        super(message);
    }

    public JsonException(String message, Throwable cause) {
        super(message, cause);
    }

    public JsonException(Throwable cause) {
        super(cause);
    }

    public JsonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
