package com.jmgits.sample.sbhcp.common.exception;

/**
 * Created by javi.more.garc on 08/01/17.
 */
public enum ErrorCode {

    //
    // general

    GENERIC(500),
    GENERIC_UNAUTHORIZED(401),
    GENERIC_FORBIDDEN(403),
    GENERIC_NOT_FOUND(404),
    GENERIC_BAD_REQUEST(400),

    //

    CATEGORY_NOT_FOUND(404),

    //
    ;
    private int status;

    ErrorCode(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
