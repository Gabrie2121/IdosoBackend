package com.idoso.backend.api.domain.dto.response;

public final class TokenDto {

    private final String type;
    private final String token;
    private final String expiration;

    public TokenDto(Builder builder) {
        this.token = builder.token;
        this.type = builder.type;
        this.expiration = builder.expiration;
    }

    public String getType() {
        return type;
    }

    public String getToken() {
        return token;
    }

    public String getExpiration() {
        return expiration;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String type;
        private String token;
        private String expiration;

        private Builder() {}

        public Builder tipo(String type) {
            this.type = type;
            return this;
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public Builder expiration(String expiration) {
            this.expiration = expiration;
            return this;
        }

        public TokenDto build() {
            return new TokenDto(this);
        }
    }
}
