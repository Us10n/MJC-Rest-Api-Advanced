package com.epam.esm.service.exception;

public class IncorrectParameterException extends RuntimeException {
    private final ExceptionHolder exceptionHolder;

    public IncorrectParameterException(ExceptionHolder exceptionHolder) {
        this.exceptionHolder = exceptionHolder;
    }

    public ExceptionHolder getExceptionHolder() {
        return exceptionHolder;
    }
}
