package com.idoso.backend.api.domain.service.contracts;

import java.util.List;

import com.idoso.backend.api.domain.entities.AnuncioEntity;
import com.idoso.backend.api.domain.exception.ObjectNotFoundException;

public interface AnuncioService {

    AnuncioEntity findById(Long id) throws ObjectNotFoundException;

    List<AnuncioEntity> findAll();
}
