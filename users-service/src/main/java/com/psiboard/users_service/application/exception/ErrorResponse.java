package com.psiboard.users_service.application.exception;

import java.time.LocalDateTime;

public class ErrorResponse {
    private boolean error;
    private String message;
    private LocalDateTime timestamp;

    public ErrorResponse(String message) {
        this.message = message;
        this.error = true;
        this.timestamp = LocalDateTime.now();
    }

    public boolean getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
