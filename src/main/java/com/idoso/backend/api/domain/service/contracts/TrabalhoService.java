package com.idoso.backend.api.domain.service.contracts;

import com.idoso.backend.api.domain.entities.TrabalhoEntity;
import com.idoso.backend.api.domain.entities.UsuarioEntity;

import java.util.List;

public interface TrabalhoService {
    List<TrabalhoEntity> findByPrestador(Long prestadorId);
}
