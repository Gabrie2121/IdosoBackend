package com.idoso.backend.api.domain.dto.request;

import com.idoso.backend.api.domain.enuns.CursoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnuncioPrestadorDTO {

    private Long idPrestador;

    private String foto;

    private String nomePrestador;

    private Double avaliacao;

    private Double ValorHora;

    private Boolean formado;

    private CursoEnum curso;

    private String whatsapp;
}
