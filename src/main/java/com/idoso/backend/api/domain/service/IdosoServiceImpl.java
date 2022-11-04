package com.idoso.backend.api.domain.service;

import com.idoso.backend.api.domain.dto.request.AnuncioPrestadorDTO;
import com.idoso.backend.api.domain.dto.response.AceitaDTO;
import com.idoso.backend.api.domain.dto.response.CandidatoDTO;
import com.idoso.backend.api.domain.dto.response.HomeUsuarioDTO;
import com.idoso.backend.api.domain.entities.AnuncioEntity;
import com.idoso.backend.api.domain.entities.CandidaturaEntity;
import com.idoso.backend.api.domain.entities.UsuarioEntity;
import com.idoso.backend.api.domain.enuns.StatusCandidaturaEnum;
import com.idoso.backend.api.domain.repository.AnuncioRepository;
import com.idoso.backend.api.domain.repository.CandidaturaRepository;
import com.idoso.backend.api.domain.repository.UsuarioRepository;
import com.idoso.backend.api.domain.service.contracts.IdosoService;
import com.idoso.backend.api.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.idoso.backend.api.domain.enuns.TipoPessoaEnum.JURIDICA;

@Service
@RequiredArgsConstructor
public final class IdosoServiceImpl implements IdosoService {

    private final UsuarioRepository usuarioRepository;
    private final FileService fileService;

    private final CandidaturaRepository candidaturaRepository;

    private final AnuncioRepository anuncioRepository;

    @Override
    public HomeUsuarioDTO getHome(Long id) {
        UsuarioEntity usuario = usuarioRepository.findById(id).get();

        List<AnuncioPrestadorDTO> anunciosPrestadorAberto = new ArrayList<>();

        List<UsuarioEntity> usuariosJuridica = usuarioRepository.buscarPorTipoPessoa(JURIDICA);

        usuariosJuridica.forEach(u -> {
            AnuncioPrestadorDTO anuncio = AnuncioPrestadorDTO
                    .builder()
                    .foto(u.getFoto())
                    .formado(u.getFormado())
                    .nomePrestador(u.getNome().concat(" ".concat(u.getSobrenome())))
                    .whatsapp(u.getCelular())
                    .ValorHora(u.getValoHora())
                    .curso(u.getCurso())
                    .avaliacao(u.getAvaliacao())
                    .build();
            anunciosPrestadorAberto.add(anuncio);
        });

        return HomeUsuarioDTO
                .builder()
                .nome(usuario.getNome())
                .avaliacao(usuario.getAvaliacao())
                .biografia(usuario.getBiografia())
                .anunciosAberto(anunciosPrestadorAberto)
                .foto(fileService.converteArquivoParaBytes(usuario.getFoto()))
                .cidade(usuario.getEndereco().getCidade())
                .build();
    }

    @Override
    public List<CandidatoDTO> getListaCandidatos(Long idAnuncio) {
        AnuncioEntity anuncio = anuncioRepository.findById(idAnuncio).get();

        List<CandidaturaEntity> candidaturas = candidaturaRepository.candidaturasByAnuncio(anuncio);

        List<CandidatoDTO> dtos = new ArrayList<>();

        candidaturas.forEach(c -> {
            UsuarioEntity prestador = c.getPrestador();

            CandidatoDTO candidato = CandidatoDTO
                    .builder()
                    .certificados(Arrays.asList("certificado 1", "certificado 2"))
                    .fotoPrestador(prestador.getFoto())
                    .valorHora(prestador.getValoHora())
                    .cidade(prestador.getEndereco().getCidade())
                    .curso(prestador.getCurso())
                    .formado(prestador.getFormado())
                    .nome(prestador.getNome().concat(" ".concat(prestador.getSobrenome())))
                    .avaliacao(prestador.getAvaliacao())
                    .biografia(prestador.getBiografia())
                    .build();

            dtos.add(candidato);
        });

        return dtos;
    }

    @Override
    public List<AceitaDTO> getListaAceitas(Long idUsuario) {
        List<AnuncioEntity> anunciosDoUsuario = usuarioRepository.findById(idUsuario).get().getAnuncios();

        List<CandidaturaEntity> candidaturas = new ArrayList<>();

        anunciosDoUsuario.stream().filter(anuncio -> !anuncio.getCandidaturas().isEmpty()).collect(Collectors.toList()).forEach(anuncio -> {
            candidaturas.addAll(anuncio.getCandidaturas());
        });

        List<AceitaDTO> aceitos = new ArrayList<>();

        candidaturas.stream()
                .filter(c -> c.getStatus() == StatusCandidaturaEnum.ACEITA)
                .forEach(c -> {
                    UsuarioEntity prestador = c.getPrestador();

                    AceitaDTO aceita = AceitaDTO
                            .builder()
                            .certificados(Arrays.asList("certificado 1", "certificado 2"))
                            .fotoPrestador(prestador.getFoto())
                            .valorHora(prestador.getValoHora())
                            .cidade(prestador.getEndereco().getCidade())
                            .curso(prestador.getCurso())
                            .formado(prestador.getFormado())
                            .nome(prestador.getNome().concat(" ".concat(prestador.getSobrenome())))
                            .avaliacao(prestador.getAvaliacao())
                            .biografia(prestador.getBiografia())
                            .build();

                    aceitos.add(aceita);
                });

        return aceitos;
    }
}
