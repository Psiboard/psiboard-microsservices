package com.psiboard.patients_service.core.application.exception;

public class CustomGenericException extends RuntimeException {
    public CustomGenericException(String message) {
        super(message);
    }
}
