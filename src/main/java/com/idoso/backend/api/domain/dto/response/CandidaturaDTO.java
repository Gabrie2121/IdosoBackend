package com.idoso.backend.api.domain.dto.response;

import com.idoso.backend.api.domain.entities.UsuarioEntity;
import com.idoso.backend.api.domain.enuns.StatusCandidaturaEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CandidaturaDTO {

    private Long prestadorId;

    private Long anuncioId;

    private StatusCandidaturaEnum statusCandidaturaEnum;

}
