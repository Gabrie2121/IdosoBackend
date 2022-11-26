package com.idoso.backend.api.service;

import com.idoso.backend.api.domain.dto.response.AceitaDTO;
import com.idoso.backend.api.domain.dto.response.AceitaPrestadorDTO;
import com.idoso.backend.api.domain.entities.AnuncioEntity;
import com.idoso.backend.api.domain.entities.CandidaturaEntity;
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

        UsuarioEntity usuario = usuarioRepository.findById(usuarioiD).get();

        List<CandidaturaEntity> minhasCandidaturas = candidaturaRepository.candidaturasDoPrestador(usuario);

        List<AceitaPrestadorDTO> aceitos = new ArrayList<>();

        minhasCandidaturas.stream()
                .filter(c -> c.getStatus() == StatusCandidaturaEnum.ACEITA)
                .forEach(c -> {
                    UsuarioEntity idoso = c.getAnuncio().getUsuario();

                    AceitaPrestadorDTO aceita = AceitaPrestadorDTO
                            .builder()
                            .fotoIdoso(idoso.getFoto())
                            .valorHora(idoso.getValoHora())
                            .cidade(idoso.getEndereco().getCidade())
                            .nome(idoso.getNome().concat(" ".concat(idoso.getSobrenome())))
                            .avaliacao(idoso.getAvaliacao())
                            .biografia(idoso.getBiografia())
                            .build();

                    aceitos.add(aceita);
                });

        return aceitos;
    }
}
