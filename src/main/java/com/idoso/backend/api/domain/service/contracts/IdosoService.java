package com.idoso.backend.api.domain.service.contracts;

import com.idoso.backend.api.domain.dto.response.HomeUsuarioDTO;
import org.springframework.stereotype.Service;

@Service
public interface IdosoService {


    HomeUsuarioDTO getHome(Long id);

}
