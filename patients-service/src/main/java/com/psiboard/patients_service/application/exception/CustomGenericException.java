package com.psiboard.patients_service.application.exception;

public class CustomGenericException extends RuntimeException {
    public CustomGenericException(String message) {
        super(message);
    }
}
