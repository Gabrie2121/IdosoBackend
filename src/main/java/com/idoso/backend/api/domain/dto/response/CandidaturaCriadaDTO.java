package com.idoso.backend.api.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CandidaturaCriadaDTO {

    private String nomeCandiato;
    private Long candidaturaId;
    private Long anuncioId;
}
