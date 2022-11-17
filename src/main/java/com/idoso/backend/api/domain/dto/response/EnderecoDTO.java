package com.idoso.backend.api.domain.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnderecoDTO {

    private Long id;

    private String cep;

    private String logradouro;

    private String cidade;

    private String uf;

    private String complemento;

    private String apelido;

    private Boolean principal;
}
