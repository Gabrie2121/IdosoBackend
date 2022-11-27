package com.idoso.backend.api.domain.service;

import com.idoso.backend.api.domain.dto.request.AnuncioPrestadorDTO;
import com.idoso.backend.api.domain.dto.response.AceitaDTO;
import com.idoso.backend.api.domain.dto.response.CandidatoDTO;
import com.idoso.backend.api.domain.dto.response.HistoricoContratosDTO;
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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
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


        AnuncioPrestadorDTO anuncio = new AnuncioPrestadorDTO();

        Iterator<UsuarioEntity> iterator = usuariosJuridica.iterator();
        while (iterator.hasNext()) {
            UsuarioEntity next = iterator.next();
            anuncio.setFoto(next.getFoto());
            anuncio.setIdPrestador(next.getId());
            anuncio.setFormado(next.getFormado());
            anuncio.setNomePrestador(next.getNomeFantasia());
            anuncio.setWhatsapp(next.getCelular());
            anuncio.setValorHora(next.getValoHora());
            anuncio.setCurso(next.getCurso());
            anuncio.setAvaliacao(next.getAvaliacao());

            anunciosPrestadorAberto.add(anuncio);

        }
        return HomeUsuarioDTO
                .builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .avaliacao(usuario.getAvaliacao())
                .biografia(usuario.getBiografia())
                .anunciosAberto(anunciosPrestadorAberto)
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

    @Override
    public List<HistoricoContratosDTO> getListaContratosRealizados(Long usuarioId) {
        UsuarioEntity parente = usuarioRepository.findById(usuarioId).orElseThrow(() ->  new UsernameNotFoundException(("Usuário não encontrado")));
        List<HistoricoContratosDTO> contratosVencidos = new ArrayList<>();

        List<AnuncioEntity> anuncios = anuncioRepository.anunciosVencidos(LocalDate.now(), parente);


        anuncios.forEach(a -> {
            HistoricoContratosDTO historico = null;
            List<CandidaturaEntity> aceitas = a.getCandidaturas().stream().filter(c-> c.getStatus() == StatusCandidaturaEnum.ACEITA).collect(Collectors.toList());
            String nomePrestador;

            if(aceitas.isEmpty()) {
                nomePrestador = "Não há prestadores para este anuncio";
            } else {
                int size = aceitas.size();
                nomePrestador = aceitas.get(size -1).getPrestador().getNomeFantasia();


            }
            historico = HistoricoContratosDTO
                    .builder()
                    .fotoPrestador("Foto Prestador")
                    .nomeIdoso(a.getIdoso().getNome() + " " + a.getIdoso().getSobrenome())
                    .horaInicio(a.getHoraInicio())
                    .horaFim(a.getHoraFim())
                    .valorHora(a.getUsuario().getValoHora())
                    .dtFim(a.getDtFim())
                    .nomePrestador(nomePrestador)
                    .avaliacao(a.getUsuario().getAvaliacao())
                    .build();

            contratosVencidos.add(historico);
        });


        return contratosVencidos;
    }
}
