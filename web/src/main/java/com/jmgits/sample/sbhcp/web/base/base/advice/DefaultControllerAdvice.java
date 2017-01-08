package com.jmgits.sample.sbhcp.web.base.base.advice;

import com.jmgits.sample.sbhcp.common.exception.ErrorCode;
import com.jmgits.sample.sbhcp.common.exception.ErrorCodeException;
import com.jmgits.sample.sbhcp.web.base.base.view.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

import static com.jmgits.sample.sbhcp.common.exception.ErrorCode.GENERIC_BAD_REQUEST;
import static com.jmgits.sample.sbhcp.common.util.ExceptionUtils.getRootMessage;
import static javax.servlet.RequestDispatcher.ERROR_STATUS_CODE;

/**
 * Created by javi.more.garc on 08/01/17.
 */
@ControllerAdvice
public class DefaultControllerAdvice {

    private final Logger logger = LoggerFactory.getLogger(DefaultControllerAdvice.class);

    @ExceptionHandler(value = ErrorCodeException.class)
    @ResponseBody
    public ErrorResponse handle(HttpServletRequest request, HttpServletResponse response, ErrorCodeException exception) {
        return generateResponse(request, response, exception, exception.getCode());
    }

    @ExceptionHandler(value = {ValidationException.class, TypeMismatchException.class, HttpMessageNotReadableException.class})
    @ResponseBody
    public ErrorResponse handle(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        return generateResponse(request, response, exception, GENERIC_BAD_REQUEST);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseBody
    public ErrorResponse handle(HttpServletRequest request, HttpServletResponse response, MethodArgumentNotValidException exception) {

        List<ObjectError> allErrors = exception.getBindingResult().getAllErrors();

        return getValidationErrorResponse(request, response, exception, allErrors);
    }

    @ExceptionHandler(value = {BindException.class})
    @ResponseBody
    public ErrorResponse handle(HttpServletRequest request, HttpServletResponse response, BindException exception) {

        List<ObjectError> allErrors = exception.getBindingResult().getAllErrors();

        return getValidationErrorResponse(request, response, exception, allErrors);

    }

    //
    // private methods

    private ErrorResponse getValidationErrorResponse(HttpServletRequest request, HttpServletResponse response, Exception exception, List<ObjectError> allErrors) {
        String errorMessage = toMessages(allErrors).stream().collect(Collectors.joining(", "));

        return generateResponse(request, response, exception, GENERIC_BAD_REQUEST, errorMessage);
    }

    private ErrorResponse generateResponse(HttpServletRequest request, HttpServletResponse response, Throwable exception, ErrorCode code) {
        return generateResponse(request, response, exception, code, getRootMessage(exception));
    }

    private ErrorResponse generateResponse(HttpServletRequest request, HttpServletResponse response, Throwable exception, ErrorCode code, String message) {

        int status = code.getStatus();

        response.setStatus(status);
        request.setAttribute(ERROR_STATUS_CODE, status);

        logger.debug("Handled exception with status '{}'", status, exception);

        return new ErrorResponse(code, message);

    }

    private List<String> toMessages(List<ObjectError> objectErrors) {

        return objectErrors.stream()
                .map(error ->
                        String.format("%s %s",
                                error.getClass().isAssignableFrom(FieldError.class) ?
                                        ((FieldError) error).getField() : error.getObjectName(),
                                error.getDefaultMessage()))
                .collect(Collectors.toList());
    }

}



