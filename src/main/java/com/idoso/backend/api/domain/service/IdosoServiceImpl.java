package com.idoso.backend.api.domain.service;

import com.idoso.backend.api.domain.repository.IdosoRepository;
import com.idoso.backend.api.domain.service.contracts.IdosoService;
import org.springframework.stereotype.Service;

@Service
public final class IdosoServiceImpl implements IdosoService {

    private final IdosoRepository idosoRepository;

    public IdosoServiceImpl(IdosoRepository idosoRepository) {
        this.idosoRepository = idosoRepository;
    }
}
