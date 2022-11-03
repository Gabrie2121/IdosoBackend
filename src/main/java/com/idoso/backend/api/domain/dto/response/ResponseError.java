package com.idoso.backend.api.domain.dto.response;

public final class ResponseError {
    private final String errorCode;
    private final String message;

    public ResponseError(Builder builder) {
        this.errorCode = builder.errorCode;
        this.message = builder.message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    public static Builder newBuilder() {
        return new Builder();
    }
    public static final class Builder {
        private String errorCode;
        private String message;

        private Builder() {}

        public Builder errorCode(String errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public ResponseError build() {
            return new ResponseError(this);
        }
    }
}
