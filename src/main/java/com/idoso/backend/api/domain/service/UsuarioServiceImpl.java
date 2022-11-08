package com.idoso.backend.api.domain.service;

import com.idoso.backend.api.domain.dto.response.CandidaturaDTO;
import com.idoso.backend.api.domain.entities.AnuncioEntity;
import com.idoso.backend.api.domain.entities.CandidaturaEntity;
import com.idoso.backend.api.domain.entities.UsuarioEntity;
import com.idoso.backend.api.domain.enuns.StatusCandidaturaEnum;
import com.idoso.backend.api.domain.repository.AnuncioRepository;
import com.idoso.backend.api.domain.repository.CandidaturaRepository;
import com.idoso.backend.api.domain.repository.UsuarioRepository;
import com.idoso.backend.api.domain.service.contracts.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public final class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final AnuncioRepository anuncioRepository;

    private final CandidaturaRepository candidaturaRepository;

    @Override
    public CandidaturaDTO novaCandidatura(CandidaturaDTO dto) {
        UsuarioEntity prestador = usuarioRepository.findById(dto.getPrestadorId()).get();
        AnuncioEntity anuncio = anuncioRepository.findById(dto.getAnuncioId()).get();

        CandidaturaEntity candidatura = candidaturaRepository.save(CandidaturaEntity
                .builder()
                .status(StatusCandidaturaEnum.ABERTA)
                .anuncio(anuncio)
                .prestador(prestador)
                .build());


        return CandidaturaDTO
                .builder()
                .statusCandidaturaEnum(candidatura.getStatus())
                .anuncioId(candidatura.getAnuncio().getId())
                .prestadorId(candidatura.getPrestador().getId())
                .build();
    }
}
