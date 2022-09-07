package com.idoso.backend.api.domain.service;

import com.idoso.backend.api.domain.entities.TrabalhoEntity;
import com.idoso.backend.api.domain.entities.UsuarioEntity;
import com.idoso.backend.api.domain.repository.AnuncioRepository;
import com.idoso.backend.api.domain.repository.TrabalhoRepository;
import com.idoso.backend.api.domain.repository.UsuarioRepository;
import com.idoso.backend.api.domain.service.contracts.AnuncioService;
import com.idoso.backend.api.domain.service.contracts.TrabalhoService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public final class TrabalhoServiceImpl implements TrabalhoService {

    private final TrabalhoRepository trabalhoRepository;
    private final UsuarioRepository usuarioRepository;


    @Override
    public List<TrabalhoEntity> findByPrestador(Long prestadorId) {
        UsuarioEntity prestador = usuarioRepository.findById(prestadorId).orElseThrow(() -> new UsernameNotFoundException("Prestador não encontrado"));
        return trabalhoRepository.findByPrestador(prestador);
    }
}
