package com.idoso.backend.api.domain.exception;

public final class CandidaturaNaoEncontradaException extends RuntimeException {

    private final String message;

    public CandidaturaNaoEncontradaException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}