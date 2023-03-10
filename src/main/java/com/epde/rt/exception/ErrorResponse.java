package com.epde.rt.exception;

public class ErrorResponse {
    private String message;
    private String details;

    public ErrorResponse(String message, String details) {
        super();
        this.message = message;
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
