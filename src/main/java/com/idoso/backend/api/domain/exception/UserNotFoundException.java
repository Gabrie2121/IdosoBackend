package com.idoso.backend.api.domain.exception;

public final class UserNotFoundException extends Exception {

    private final String message;

    public UserNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}