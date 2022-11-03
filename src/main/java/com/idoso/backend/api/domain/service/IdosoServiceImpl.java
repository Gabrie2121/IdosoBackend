package com.idoso.backend.api.domain.service;

import com.idoso.backend.api.domain.dto.request.AnuncioPrestadorDTO;
import com.idoso.backend.api.domain.dto.response.AnuncioDoUsuarioDTO;
import com.idoso.backend.api.domain.dto.response.HomeUsuarioDTO;
import com.idoso.backend.api.domain.entities.AnuncioEntity;
import com.idoso.backend.api.domain.entities.UsuarioEntity;
import com.idoso.backend.api.domain.enuns.TipoPessoaEnum;
import com.idoso.backend.api.domain.exception.UserNotFoundException;
import com.idoso.backend.api.domain.repository.AnuncioRepository;
import com.idoso.backend.api.domain.repository.UsuarioRepository;
import com.idoso.backend.api.domain.service.contracts.IdosoService;
import com.idoso.backend.api.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.idoso.backend.api.domain.enuns.TipoPessoaEnum.JURIDICA;

@Service
@RequiredArgsConstructor
public final class IdosoServiceImpl implements IdosoService {

    private final UsuarioRepository usuarioRepository;
    private final FileService fileService;


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
}
