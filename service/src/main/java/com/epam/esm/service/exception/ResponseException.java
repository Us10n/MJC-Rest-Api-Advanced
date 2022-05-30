package com.epam.esm.service.exception;

import org.springframework.http.HttpStatus;

import java.util.Locale;

public class ResponseException extends RuntimeException {
    private final HttpStatus httpStatus;
    private Locale locale;
    private Object[] params;

    public ResponseException(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public ResponseException(HttpStatus httpStatus, Object[] params) {
        this.httpStatus = httpStatus;
        this.params = params;
    }

    public ResponseException(HttpStatus httpStatus, Locale locale) {
        this.httpStatus = httpStatus;
        this.locale = locale;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public Locale getLocale() {
        return locale;
    }

    public Object[] getParams() {
        return params;
    }
}
