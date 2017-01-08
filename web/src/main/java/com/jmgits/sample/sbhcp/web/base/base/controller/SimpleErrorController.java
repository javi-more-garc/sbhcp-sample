package com.jmgits.sample.sbhcp.web.base.base.controller;


import com.jmgits.sample.sbhcp.web.base.base.view.ErrorResponse;
import org.apache.catalina.connector.ResponseFacade;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import static java.util.Optional.ofNullable;
import static javax.servlet.RequestDispatcher.ERROR_STATUS_CODE;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.util.Assert.notNull;

/**
 * Created by javi.more.garc on 03/10/16.
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

        return generateResponse(request, response, ofNullable(exception)
                .map(Throwable::getMessage)
                .orElse("General error")
        );
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

    private ErrorResponse generateResponse(HttpServletRequest request, HttpServletResponse response, String message) {

        response.setStatus(500);
        request.setAttribute(ERROR_STATUS_CODE, 500);

        return new ErrorResponse(message);

    }
}