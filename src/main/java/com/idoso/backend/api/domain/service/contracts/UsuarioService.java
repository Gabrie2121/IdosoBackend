package com.idoso.backend.api.domain.service.contracts;


import com.idoso.backend.api.domain.dto.response.CandidaturaDTO;
import com.idoso.backend.api.domain.dto.response.HomePrestadorDTO;

public interface UsuarioService {
    CandidaturaDTO novaCandidatura(CandidaturaDTO dto);

    HomePrestadorDTO getHomePrestador(Long parseLong);
}
