package com.jmgits.sample.sbhcp.web.base.base.controller;

import com.jmgits.sample.sbhcp.common.exception.ErrorCode;
import com.jmgits.sample.sbhcp.common.exception.ErrorCodeException;
import com.jmgits.sample.sbhcp.web.base.base.view.ErrorResponse;
import org.apache.catalina.connector.ResponseFacade;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.util.Map;
import java.util.Optional;

import static com.jmgits.sample.sbhcp.common.exception.ErrorCode.*;
import static com.jmgits.sample.sbhcp.common.util.ExceptionUtils.getRootMessage;
import static javax.servlet.RequestDispatcher.ERROR_STATUS_CODE;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.util.Assert.notNull;

/**
 * Created by javi.more.garc on 08/01/17.
 */
@RestController
@RequestMapping("/error")
public class SimpleErrorController implements ErrorController {

    private final ErrorAttributes errorAttributes;

    @Inject
    public SimpleErrorController(ErrorAttributes errorAttributes) {
        notNull(errorAttributes);

        this.errorAttributes = errorAttributes;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping
    public ErrorResponse error(HttpServletRequest request, HttpServletResponse response) {

        //
        // should we have an uncontrolled exception (like SqlException), the
        // ErrorPageFilter would leave a 200 when running in a real tomcat

        forceToInternalServerErrorIfOk(response);

        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        Throwable exception = this.errorAttributes.getError(requestAttributes);

        if (!Optional.ofNullable(exception).isPresent()) {

            Map<String, Object> requestErrorAttributes =
                    this.errorAttributes.getErrorAttributes(new ServletRequestAttributes(request), false);

            ErrorCode code = errorCode(requestErrorAttributes);

            return generateResponse(request, response, code, HttpStatus.valueOf(code.getStatus()).getReasonPhrase());
        }

        ErrorCode errorCode = getErrorCode(exception);

        return generateResponse(request, response, errorCode, getRootMessage(exception));
    }

    //
    // private methods

    private void forceToInternalServerErrorIfOk(HttpServletResponse response) {

        ResponseFacade responseFacade = getResponseFacade(response);

        // if there is a no response facade or it contains an error status
        if (responseFacade == null || responseFacade.getStatus() >= 300) {
            // nothing to do
            return;
        }

        // force the status to internal server error
        responseFacade.setStatus(INTERNAL_SERVER_ERROR.value());

    }

    private ErrorCode getErrorCode(Throwable exception) {

        boolean errorCodeException = exception.getClass().isAssignableFrom(ErrorCodeException.class);

        return errorCodeException ? ((ErrorCodeException) exception).getCode() : GENERIC;
    }

    private ResponseFacade getResponseFacade(HttpServletResponse response) {

        if (!(response instanceof HttpServletResponseWrapper) && !(response instanceof ResponseFacade)) {
            return null;
        }

        if (response instanceof HttpServletResponseWrapper) {

            HttpServletResponse wrapped = (HttpServletResponse) ((HttpServletResponseWrapper) response).getResponse();

            return getResponseFacade(wrapped);
        }

        return (ResponseFacade) response;

    }

    private ErrorResponse generateResponse(HttpServletRequest request, HttpServletResponse response, ErrorCode code, String message) {

        int status = code.getStatus();

        response.setStatus(status);
        request.setAttribute(ERROR_STATUS_CODE, status);

        return new ErrorResponse(code, message);

    }

    private ErrorCode errorCode(Map<String, Object> errorAttributes) {

        Integer status = (Integer) errorAttributes.getOrDefault("status", 500);

        if (status == 400) {
            return GENERIC_BAD_REQUEST;
        }

        if (status == 401) {
            return GENERIC_UNAUTHORIZED;
        }

        if (status == 403) {
            return GENERIC_FORBIDDEN;
        }

        if (status == 404) {
            return GENERIC_NOT_FOUND;
        }

        return GENERIC;

    }
}