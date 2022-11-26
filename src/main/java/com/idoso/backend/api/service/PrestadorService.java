package com.idoso.backend.api.service;

import com.idoso.backend.api.domain.dto.response.AceitaDTO;
import com.idoso.backend.api.domain.dto.response.AceitaPrestadorDTO;
import com.idoso.backend.api.domain.entities.AnuncioEntity;
import com.idoso.backend.api.domain.entities.CandidaturaEntity;
import com.idoso.backend.api.domain.entities.IdosoEntity;
import com.idoso.backend.api.domain.entities.UsuarioEntity;
import com.idoso.backend.api.domain.enuns.StatusCandidaturaEnum;
import com.idoso.backend.api.domain.repository.CandidaturaRepository;
import com.idoso.backend.api.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PrestadorService {

    private final CandidaturaRepository candidaturaRepository;
    private final UsuarioRepository usuarioRepository;
    public List<AceitaPrestadorDTO> getListaAceitas(Long usuarioiD) {

        UsuarioEntity prestador = usuarioRepository.findById(usuarioiD).get();

        List<CandidaturaEntity> minhasCandidaturas = candidaturaRepository.candidaturasDoPrestador(prestador);

        List<AceitaPrestadorDTO> aceitos = new ArrayList<>();

        minhasCandidaturas.stream()
                .filter(c -> c.getStatus() == StatusCandidaturaEnum.ACEITA)
                .forEach(c -> {
                    IdosoEntity idoso = c.getAnuncio().getIdoso();

                    UsuarioEntity usuario = c.getAnuncio().getUsuario();

                    AceitaPrestadorDTO aceita = AceitaPrestadorDTO
                            .builder()
                            .fotoIdoso("FotoIdoso")
                            .valorHora(c.getAnuncio().getPagamentoBase().doubleValue())
                            .cidade(usuario.getEndereco().getCidade())
                            .nome(usuario.getNome() + " " + usuario.getSobrenome())
                            .avaliacao(usuario.getAvaliacao())
                            .biografia(usuario.getBiografia())
                            .nomeIdoso(idoso.getNome() + " " + idoso.getSobrenome())
                            .build();

                    aceitos.add(aceita);
                });

        return aceitos;
    }
}
