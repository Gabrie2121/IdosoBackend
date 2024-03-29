package com.idoso.backend.api.service;

import com.idoso.backend.api.domain.dto.response.AceitaPrestadorDTO;
import com.idoso.backend.api.domain.dto.response.HistoricoTrabalhosDTO;
import com.idoso.backend.api.domain.entities.CandidaturaEntity;
import com.idoso.backend.api.domain.entities.IdosoEntity;
import com.idoso.backend.api.domain.entities.UsuarioEntity;
import com.idoso.backend.api.domain.enuns.StatusCandidaturaEnum;
import com.idoso.backend.api.domain.enuns.TipoPessoaEnum;
import com.idoso.backend.api.domain.repository.CandidaturaRepository;
import com.idoso.backend.api.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
                            .build()
                            ;

                    aceitos.add(aceita);
                });

        return aceitos;
    }

    public List<HistoricoTrabalhosDTO> getHistoricoContratos(String usuarioId) {

        UsuarioEntity prestador = usuarioRepository.findById(Long.parseLong(usuarioId)).get();

        List<HistoricoTrabalhosDTO> listaTrabalhos = new ArrayList<>();
        List<CandidaturaEntity> candidaturasVencidas = new ArrayList<>();
        try {
            candidaturasVencidas = candidaturaRepository.candidaturasVencidas(LocalDate.now(), prestador);
        } catch (Exception e) {
            System.out.println(e);
        }

        candidaturasVencidas.forEach(c -> {
            UsuarioEntity parente = c.getAnuncio().getUsuario();
            HistoricoTrabalhosDTO trabalho = HistoricoTrabalhosDTO
                    .builder()
                    .id(UUID.randomUUID().toString())
                    .fotoIdoso("Foto do Idoso")
                    .avaliacao(parente.getAvaliacao())
                    .nomeParente(parente.getTipoPessoa() == TipoPessoaEnum.FISICA ? parente.getNome() + " " + parente.getSobrenome() : parente.getNomeFantasia())
                    .horaInicio(c.getAnuncio().getHoraInicio())
                    .horaFim(c.getAnuncio().getHoraFim())
                    .nomeIdoso(c.getAnuncio().getIdoso().getNome() + " " + c.getAnuncio().getIdoso().getSobrenome())
                    .valorHora(c.getAnuncio().getPagamentoBase().doubleValue())
                    .dataFim(c.getAnuncio().getDtFim())
                    .build();

        listaTrabalhos.add(trabalho);

        });


        return listaTrabalhos;

    }
}
