package com.idoso.backend.api.domain.exception;

public final class DocumentoNaoEncontradoException extends Exception {

    private final String message;

    public DocumentoNaoEncontradoException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}