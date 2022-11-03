package com.idoso.backend.api.domain.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("idoso.jwt")
public class JwtProperties {

    private String tokenExpirationTime;
    private String appSecret;

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public void setTokenExpirationTime(String tokenExpirationTime) {
        this.tokenExpirationTime = tokenExpirationTime;
    }

    public String getTokenExpirationTime() {
        return tokenExpirationTime;
    }

    public String getAppSecret() {
        return appSecret;
    }
}
