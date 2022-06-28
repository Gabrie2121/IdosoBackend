package com.idoso.backend.api.domain.service;

import com.idoso.backend.api.domain.repository.LaudoRepository;
import com.idoso.backend.api.domain.service.contracts.LaudoService;
import org.springframework.stereotype.Service;

@Service
public final class LaudoServiceImpl implements LaudoService {

    private final LaudoRepository laudoRepository;

    public LaudoServiceImpl(LaudoRepository laudoRepository) {
        this.laudoRepository = laudoRepository;
    }
}
