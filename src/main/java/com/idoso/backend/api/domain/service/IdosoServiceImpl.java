package com.idoso.backend.api.domain.service;

import com.idoso.backend.api.domain.dto.response.AnuncioPrestadorDTO;
import com.idoso.backend.api.domain.dto.response.HomeUsuarioDTO;
import com.idoso.backend.api.domain.entities.UsuarioEntity;
import com.idoso.backend.api.domain.repository.IdosoRepository;
import com.idoso.backend.api.domain.repository.UsuarioRepository;
import com.idoso.backend.api.domain.service.contracts.IdosoService;
import com.idoso.backend.api.service.FileService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public final class IdosoServiceImpl implements IdosoService {

    private final UsuarioRepository usuarioRepository;

    private final FileService fileService;

    @Override
    public HomeUsuarioDTO getHome(Long id) {
        UsuarioEntity usuario = usuarioRepository.findById(id).get();

        return HomeUsuarioDTO
                .builder()
                .nome(usuario.getNome())
                .avaliacao(usuario.getAvaliacao())
                .biografia(usuario.getBiografia())
                .foto(fileService.converteArquivoParaBytes(usuario.getFoto()))
                .anuncios(Arrays.asList(
                        AnuncioPrestadorDTO.builder()
                                .foto("bytes da foto")
                                .formado("FORMADO")
                                .curso("Enfermagem")
                                .valorHora(200.00)
                                .avaliacao(usuario.getAvaliacao())
                                .whatsapp("9999999")
                                .build(),

                        AnuncioPrestadorDTO.builder()
                                .foto("bytes da foto")
                                .formado("FORMADO")
                                .curso("Enfermagem")
                                .valorHora(200.00)
                                .avaliacao(usuario.getAvaliacao())
                                .whatsapp("9999999")
                                .build()
                ))
                .cidade(usuario.getEndereco().getCidade())
                .build();
    }
}
