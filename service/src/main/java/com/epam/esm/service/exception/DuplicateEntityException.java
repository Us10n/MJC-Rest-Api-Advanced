package com.epam.esm.service.exception;

public class DuplicateEntityException extends RuntimeException{
    public DuplicateEntityException() {
    }

    public DuplicateEntityException(String messageKey) {
        super(messageKey);
    }

    public DuplicateEntityException(String messageKey, Throwable cause) {
        super(messageKey, cause);
    }

    public DuplicateEntityException(Throwable cause) {
        super(cause);
    }
}
