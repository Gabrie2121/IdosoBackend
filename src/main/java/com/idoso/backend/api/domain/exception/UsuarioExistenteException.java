package com.idoso.backend.api.domain.exception;

public final class UsuarioExistenteException extends Exception {

    private final String message;

    public UsuarioExistenteException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}