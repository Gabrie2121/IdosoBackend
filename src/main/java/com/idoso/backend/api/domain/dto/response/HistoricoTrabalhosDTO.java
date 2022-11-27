package com.idoso.backend.api.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoricoTrabalhosDTO {

    private String fotoIdoso;

    private Double avaliacao;

    private String data;

    private String horaInicio;

    private String horaFim;

    private String nomeParente;

    private String nomeIdoso;

    private Double valorHora;
}
