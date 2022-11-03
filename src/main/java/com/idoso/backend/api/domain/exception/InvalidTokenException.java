package com.idoso.backend.api.domain.exception;

public final class InvalidTokenException extends Exception {

    private final String message;

    public InvalidTokenException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
