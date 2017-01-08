package com.jmgits.sample.sbhcp.common.util;

import java.util.Optional;

import static org.apache.commons.lang3.exception.ExceptionUtils.getRootCause;

/**
 * Created by javi.more.garc on 08/01/17.
 */
public final class ExceptionUtils {

    private ExceptionUtils() {
    }

    public static String getRootMessage(Throwable throwable){

        Throwable rootCause = getRootCause(throwable);

        return Optional.ofNullable(rootCause).orElse(throwable).getMessage();

    }
}
