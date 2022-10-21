package com.idoso.backend.api.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class TokenDto {
    private final String type;
    private final String token;
    private final String expiration;

}
