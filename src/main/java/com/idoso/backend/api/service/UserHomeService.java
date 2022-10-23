package com.idoso.backend.api.service;

import com.idoso.backend.api.domain.dto.response.HomeUsuarioDTO;
import com.idoso.backend.api.domain.entities.AnuncioEntity;
import com.idoso.backend.api.domain.entities.EnderecoEntity;
import com.idoso.backend.api.domain.entities.UsuarioEntity;
import com.idoso.backend.api.domain.repository.AnuncioRepository;
import com.idoso.backend.api.domain.repository.EnderecoRepository;
import com.idoso.backend.api.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserHomeService {

    private final FileService fileService;

    private final UsuarioRepository usuarioRepository;

    private final AnuncioRepository anuncioRepository;

    private final EnderecoRepository enderecoRepository;
    public HomeUsuarioDTO getHomeData(UsuarioEntity usuarioEntity) {
        UsuarioEntity usuario = usuarioRepository.findById(usuarioEntity.getId()).get();


        String bytes = fileService.converteArquivoParaBytes(usuario.getFoto());

        return HomeUsuarioDTO
                .builder()
                .foto(bytes)
                .cidade(usuario.getEndereco().getCidade())
                .biografia(usuario.getBiografia())
                .avaliacao(usuario.getAvaliacao())
                .build();
    }
}
