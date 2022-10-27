package com.idoso.backend.api.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AnuncioDoUsuarioDTO {

    private Long id;

    private String foto;

    private Double avaliacao;

    private Double valorHora;

    private String formado;

    private String curso;

    private String whatsapp;

    private String nomeIdoso;
}
