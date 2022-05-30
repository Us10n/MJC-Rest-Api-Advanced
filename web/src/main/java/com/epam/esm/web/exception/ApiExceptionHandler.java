package com.epam.esm.web.exception;

import com.epam.esm.service.exception.ResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static com.epam.esm.web.exception.ExceptionMessage.*;

@ControllerAdvice
public class ApiExceptionHandler {
    public static final String VERSION = " custom";
    private static final String ERROR_MESSAGE = "errorMessage";
    private static final String ERROR_CODE = "errorCode";
    private MessageSource messageSource;

    @Autowired
    public ApiExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(ResponseException.class)
    public ResponseEntity<Map<String, String>> handleApiResponseException(ResponseException e, Locale locale) {
        Map<String, String> errorResponse = new HashMap<>();

        String message = messageSource.getMessage(ERROR + e.getHttpStatus().name().toLowerCase(), null, locale);
        if (e.getParams() != null) {
            final String infoMessage = e.getParams().length > 1 ?
                    messageSource.getMessage(INFO + WRONG_PARAMETERS_VALUES, e.getParams(), locale) :
                    messageSource.getMessage(INFO + WRONG_DATE_FORMAT, null, locale);
            message += ", " + infoMessage;
        }

        errorResponse.put(ERROR_MESSAGE, message);
        errorResponse.put(ERROR_CODE, e.getHttpStatus().value() + VERSION);
        return new ResponseEntity<>(errorResponse, e.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, String>> hadleArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, Locale locale) {
        Map<String, String> errorResponse = new HashMap<>();
        String message = messageSource.getMessage(INFO + WRONG_ARGS_TYPE, new Object[]{ex.getValue(), ex.getRequiredType()}, locale);

        errorResponse.put(ERROR_MESSAGE, message);
        errorResponse.put(ERROR_CODE, HttpStatus.BAD_REQUEST.value() + VERSION);
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleException(Exception ex, Locale locale) {
        Map<String, String> errorResponse = new HashMap<>();
        String message = messageSource.getMessage(ERROR + UNEXPECTED, null, locale);

        errorResponse.put(ERROR_MESSAGE, message);
        errorResponse.put(ERROR_CODE, HttpStatus.INTERNAL_SERVER_ERROR.value() + VERSION);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
