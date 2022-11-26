package com.idoso.backend.api.domain.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IdsDTO {
    Long posicao;
    Long id;
}
