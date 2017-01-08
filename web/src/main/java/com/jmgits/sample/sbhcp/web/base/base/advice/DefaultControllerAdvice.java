package com.jmgits.sample.sbhcp.web.base.base.advice;

import com.jmgits.sample.sbhcp.web.base.base.view.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static javax.servlet.RequestDispatcher.ERROR_STATUS_CODE;

/**
 * Created by javi.more.garc on 03/10/16.
 */
@ControllerAdvice
public class DefaultControllerAdvice {

    private final Logger logger = LoggerFactory.getLogger(DefaultControllerAdvice.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ErrorResponse handle(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        return generateResponse(request, response, exception);
    }


    private ErrorResponse generateResponse(HttpServletRequest request, HttpServletResponse response, Exception exception) {

        response.setStatus(500);
        request.setAttribute(ERROR_STATUS_CODE, 500);

        logger.debug("Handled exception '{}'", exception);

        return new ErrorResponse(exception.getMessage());

    }

}



