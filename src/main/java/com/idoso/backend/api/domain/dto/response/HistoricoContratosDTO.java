package com.idoso.backend.api.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoricoContratosDTO {

    private String fotoPrestador;

    private String nomePrestador;

    private LocalDate dtFim;

    private String horaInicio;

    private String horaFim;

    private Double avaliacao;

    private String nomeIdoso;

    private Double valorHora;
}
