package com.idoso.backend.api.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idoso.backend.api.domain.entities.AnuncioEntity;
import com.idoso.backend.api.domain.exception.ObjectNotFoundException;
import com.idoso.backend.api.domain.repository.AnuncioRepository;
import com.idoso.backend.api.domain.service.contracts.AnuncioService;

@Service
public final class AnuncioServiceImpl implements AnuncioService {

    @Autowired
    private final AnuncioRepository anuncioRepository;

    public AnuncioServiceImpl(AnuncioRepository anuncioRepository) {
        this.anuncioRepository = anuncioRepository;
    }

    @Override
    public AnuncioEntity findById(Long id) throws ObjectNotFoundException {
        Optional<AnuncioEntity> obj = anuncioRepository.findById(id);
        if (obj.isPresent()) {
            return obj.get();
        }
        throw new ObjectNotFoundException("Anúncio não encontrado");
    }

    public List<AnuncioEntity> findAll() {
        return anuncioRepository.findAll();
    }
}
