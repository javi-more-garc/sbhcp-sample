package com.jmgits.sample.sbhcp.web.base.base.view;

import com.jmgits.sample.sbhcp.common.exception.ErrorCode;

import java.io.Serializable;

/**
 * Created by javi.more.garc on 08/01/17.
 */
public class ErrorResponse implements Serializable {

    private ErrorCode code;
    private String message;

    public ErrorResponse() {
        // default constructor
    }

    public ErrorResponse(ErrorCode code, String message) {
        this.code = code;
        this.message = message;
    }

    public ErrorCode getCode() {
        return code;
    }

    public void setCode(ErrorCode code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return code.getStatus();
    }
}
