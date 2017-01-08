package com.jmgits.sample.sbhcp.web.base.base.view;

import java.io.Serializable;

/**
 * Created by javi.more.garc on 30/11/16.
 */
public class ErrorResponse implements Serializable {

    private String message;

    public ErrorResponse() {
        // default constructor
    }

    public ErrorResponse(String message) {
        this.message = message;
    }


    public String getMessage() {
        return message;
    }
}
