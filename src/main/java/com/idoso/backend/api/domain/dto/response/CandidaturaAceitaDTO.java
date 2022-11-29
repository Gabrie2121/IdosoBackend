package com.idoso.backend.api.domain.dto.response;

import com.idoso.backend.api.domain.enuns.StatusCandidaturaEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CandidaturaAceitaDTO {

    private Long anuncioId;
    private Long candidaturaId;
    private Long prestadorId;
    private String nomeIdoso;
    private StatusCandidaturaEnum status;
}
