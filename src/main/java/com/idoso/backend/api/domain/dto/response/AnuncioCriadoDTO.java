package com.idoso.backend.api.domain.dto.response;

import com.idoso.backend.api.domain.enuns.PeriodoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnuncioCriadoDTO {

    private Long id;

    private String nomeIdoso;

    private Double avaliacao;

    private String fotoIdoso;

    private Boolean isPcd;

    private PeriodoEnum periodoEnum;

    private Double valor;
}
