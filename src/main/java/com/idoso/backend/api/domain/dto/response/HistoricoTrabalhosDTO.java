package com.idoso.backend.api.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoricoTrabalhosDTO {

    private String id;

    private String fotoIdoso;

    private Double avaliacao;

    private LocalDate dataFim;

    private String horaInicio;

    private String horaFim;

    private String nomeParente;

    private String nomeIdoso;

    private Double valorHora;
}
