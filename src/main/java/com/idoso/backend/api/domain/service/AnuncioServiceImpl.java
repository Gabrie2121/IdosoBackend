package com.idoso.backend.api.domain.service;

import com.idoso.backend.api.domain.repository.AnuncioRepository;
import com.idoso.backend.api.domain.service.contracts.AnuncioService;
import org.springframework.stereotype.Service;

@Service
public final class AnuncioServiceImpl implements AnuncioService {

    private final AnuncioRepository anuncioRepository;

    public AnuncioServiceImpl(AnuncioRepository anuncioRepository) {
        this.anuncioRepository = anuncioRepository;
    }
}
