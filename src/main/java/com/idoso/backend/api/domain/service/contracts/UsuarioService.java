package com.idoso.backend.api.domain.service.contracts;


import com.idoso.backend.api.domain.dto.response.CandidaturaDTO;

public interface UsuarioService {
    CandidaturaDTO novaCandidatura(CandidaturaDTO dto);
}
