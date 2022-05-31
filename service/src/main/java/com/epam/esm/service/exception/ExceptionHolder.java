package com.epam.esm.service.exception;

import java.util.HashMap;
import java.util.Map;

public class ExceptionHolder {
    private final Map<String, Object[]> exceptionMessages;

    public ExceptionHolder() {
        this.exceptionMessages = new HashMap<>();
    }

    public void addException(String messageCode, Object... args) {
        exceptionMessages.put(messageCode, args);
    }

    public Map<String, Object[]> getExceptionMessages() {
        return new HashMap<>(exceptionMessages);
    }
}
