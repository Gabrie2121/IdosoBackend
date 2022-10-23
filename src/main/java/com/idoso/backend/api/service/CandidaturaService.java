package com.idoso.backend.api.service;

import com.idoso.backend.api.domain.dto.response.CandidaturaCriadaDTO;
import com.idoso.backend.api.domain.entities.AnuncioEntity;
import com.idoso.backend.api.domain.entities.CandidaturaPrestadorEntity;
import com.idoso.backend.api.domain.entities.CursoPrestadorEntity;
import com.idoso.backend.api.domain.entities.UsuarioEntity;
import com.idoso.backend.api.domain.repository.AnuncioRepository;
import com.idoso.backend.api.domain.repository.CandidaturaRepository;
import com.idoso.backend.api.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CandidaturaService {

    private final CandidaturaRepository candidaturaRepository;
    private final AnuncioRepository anuncioRepository;

    private final UsuarioRepository usuarioRepository;

    private final FileService fileService;


    public CandidaturaCriadaDTO novaCandidatura(CandidaturaPrestadorEntity candidaturaData) {
        AnuncioEntity anuncio = anuncioRepository.findById(candidaturaData.getAnuncio().getId()).get();
        UsuarioEntity prestador = usuarioRepository.findById(candidaturaData.getPrestador().getId()).get();


        CandidaturaPrestadorEntity candidatura = CandidaturaPrestadorEntity
                .builder()
                .situacao(candidaturaData.getSituacao())
                .pagamentoBase(candidaturaData.getPagamentoBase())
                .anuncio(anuncio)
                .prestador(prestador)
                .build();

        CandidaturaPrestadorEntity candidaturaSalva = candidaturaRepository.save(candidatura);


        List<CursoPrestadorEntity> cursos = candidaturaData.getCursos();



        return null;
    }
}
