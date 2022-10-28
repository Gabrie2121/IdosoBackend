package com.idoso.backend.api.domain.dto.response;

import com.idoso.backend.api.domain.enuns.CursoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CandidaturaAnuncioDTO {

    private String fotoPrestador;

    private String foto;

    private String nomePrestador;

    private List<String> laudos;

    private Boolean formado;

    private String formacao;

    private String biografiaPrestador;

    private Long id;

    private Double avaliacao;

    private Double valorHora;

    private CursoEnum curso;

    private String whatsapp;

    private String nomeIdoso;
}
