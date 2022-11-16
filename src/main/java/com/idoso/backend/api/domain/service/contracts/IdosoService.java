package com.idoso.backend.api.domain.service.contracts;

import com.idoso.backend.api.domain.dto.response.AceitaDTO;
import com.idoso.backend.api.domain.dto.response.CandidatoDTO;
import com.idoso.backend.api.domain.dto.response.HomeUsuarioDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IdosoService {


    HomeUsuarioDTO getHome(Long id);
    List<CandidatoDTO> getListaCandidatos(Long idAnuncio);

    List<AceitaDTO> getListaAceitas(Long parseLong);

}
