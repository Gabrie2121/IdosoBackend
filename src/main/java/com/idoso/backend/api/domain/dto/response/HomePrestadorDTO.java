package com.idoso.backend.api.domain.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class HomePrestadorDTO {
    private String foto;

    private Double avaliacao;

    private String cidade;

    private String biografia;

    private String nome;
}
