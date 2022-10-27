package com.idoso.backend.api.domain.service;

import com.idoso.backend.api.domain.dto.response.AnuncioDoUsuarioDTO;
import com.idoso.backend.api.domain.dto.response.HomeUsuarioDTO;
import com.idoso.backend.api.domain.entities.AnuncioEntity;
import com.idoso.backend.api.domain.entities.UsuarioEntity;
import com.idoso.backend.api.domain.repository.AnuncioRepository;
import com.idoso.backend.api.domain.repository.UsuarioRepository;
import com.idoso.backend.api.domain.service.contracts.IdosoService;
import com.idoso.backend.api.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public final class IdosoServiceImpl implements IdosoService {

    private final UsuarioRepository usuarioRepository;

    private final FileService fileService;

    private final AnuncioRepository anuncioRepository;

    @Override
    public HomeUsuarioDTO getHome(Long id) {
        UsuarioEntity usuario = usuarioRepository.findById(id).get();
        List<AnuncioEntity> anunciosDoBanco = anuncioRepository.findByUsuarioId(usuario.getId());
        List<AnuncioDoUsuarioDTO> anunciosDto = new ArrayList<>();

        anunciosDoBanco.forEach(anuncio -> {
            AnuncioDoUsuarioDTO anuncioDto = AnuncioDoUsuarioDTO
                    .builder()
                    .id(anuncio.getId())
                    .foto("teste")
                    .avaliacao(anuncio.getUsuario().getAvaliacao())
                    .formado("TESTE")
                    .curso("TESTE")
                    .nomeIdoso(anuncio.getIdoso().getNome())
                    .valorHora(anuncio.getPagamentoBase().doubleValue())
                    .whatsapp(anuncio.getUsuario().getCelular())
                    .build();

            anunciosDto.add(anuncioDto);
        });



        return HomeUsuarioDTO
                .builder()
                .nome(usuario.getNome())
                .avaliacao(usuario.getAvaliacao())
                .biografia(usuario.getBiografia())
                .foto(fileService.converteArquivoParaBytes(usuario.getFoto()))
                .anuncios(anunciosDto)
                .cidade(usuario.getEndereco().getCidade())
                .build();
    }
}
