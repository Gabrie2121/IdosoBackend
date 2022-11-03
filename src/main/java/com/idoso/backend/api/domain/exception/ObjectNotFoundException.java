package com.idoso.backend.api.domain.exception;

public final class ObjectNotFoundException extends Exception {

    private final String message;

    public ObjectNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}