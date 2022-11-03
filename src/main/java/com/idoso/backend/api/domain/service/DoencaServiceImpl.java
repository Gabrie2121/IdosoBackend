package com.idoso.backend.api.domain.service;

import com.idoso.backend.api.domain.repository.DoencaRepository;
import com.idoso.backend.api.domain.service.contracts.DoencaService;
import org.springframework.stereotype.Service;

@Service
public final class DoencaServiceImpl implements DoencaService {

    private final DoencaRepository doencaRepository;

    public DoencaServiceImpl(DoencaRepository doencaRepository) {
        this.doencaRepository = doencaRepository;
    }
}
