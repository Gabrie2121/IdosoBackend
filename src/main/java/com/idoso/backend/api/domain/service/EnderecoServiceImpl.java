package com.idoso.backend.api.domain.service;

import com.idoso.backend.api.domain.repository.EnderecoRepository;
import com.idoso.backend.api.domain.service.contracts.EnderecoService;
import org.springframework.stereotype.Service;

@Service
public final class EnderecoServiceImpl implements EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public EnderecoServiceImpl(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }
}
