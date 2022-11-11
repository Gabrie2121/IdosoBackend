package com.idoso.backend.api.controller;

import com.idoso.backend.api.domain.entities.CursoPrestadorEntity;
import com.idoso.backend.api.domain.entities.EnderecoEntity;
import com.idoso.backend.api.domain.entities.ProfileEntity;
import com.idoso.backend.api.domain.entities.UsuarioEntity;
import com.idoso.backend.api.domain.enuns.TipoPessoaEnum;
import com.idoso.backend.api.domain.exception.DocumentoNaoEncontradoException;
import com.idoso.backend.api.domain.exception.ObjectNotFoundException;
import com.idoso.backend.api.domain.exception.UsuarioExistenteException;
import com.idoso.backend.api.domain.repository.CursoPrestadorRepository;
import com.idoso.backend.api.domain.repository.EnderecoRepository;
import com.idoso.backend.api.domain.repository.ProfileRepository;
import com.idoso.backend.api.domain.repository.UsuarioRepository;
import com.idoso.backend.api.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/open")
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
public class CadastroController {

    private final UsuarioRepository usuarioRepository;

    private final FileService fileService;

    private final PasswordEncoder encoder;

    private final ProfileRepository profileRepository;

    private final EnderecoRepository enderecoRepository;
    @Value("${idoso.profileFolder}")
    private String profileFolder;

    @PostMapping("/cadastro/usuario")
    public UsuarioEntity post(@Validated @RequestBody UsuarioEntity usuario) throws DocumentoNaoEncontradoException, ObjectNotFoundException, UsuarioExistenteException {

        if(Objects.isNull(usuario.getNDoc()) || usuario.getNDoc().isEmpty()) {
            throw new DocumentoNaoEncontradoException("O documento é obrigatório");

        } else if(Objects.isNull(usuario.getFoto()) || usuario.getFoto().isEmpty()) {
            throw new ObjectNotFoundException("A foto é obrigatória");
        }

        Optional<UsuarioEntity> byUsername = usuarioRepository.findByUsername(usuario.getUsername());
        if(byUsername.isPresent()) {
            throw new UsuarioExistenteException("Este usuário já foi cadastrado");
        }

        usuario.setPassword(encoder.encode(usuario.getPassword()));

        usuario.setAvaliacao(0.0);

        salvarFotoPerfil(usuario);

        salvarEndereco(usuario);

        setProfiles(usuario);

        if(usuario.getTipoPessoa() == TipoPessoaEnum.JURIDICA) {
            String separador = getSeparator();
            final int[] count = {1};

            usuario.getCertificados().forEach(c -> {
                String path = profileFolder + separador + usuario.getNDoc() + " certificado " +
                        String.format("%04d", count[0])+".pdf";
                count[0]++;
                fileService.converteBytesParaArquivo(path, c);
            });
        }



        return usuarioRepository.save(usuario);

    }

    private void salvarFotoPerfil(UsuarioEntity usuario) {
        String temp = usuario.getFoto();
        String separator = getSeparator();
        String path = profileFolder + separator + usuario.getNDoc() + "profile.jpg";
        usuario.setFoto(path);
        salvarFotoDePerfil(temp, path);
    }

    private  String getSeparator() {
        String osName = System.getProperty("os.name");
       return osName.contains("Windows") ? "\\" : "/";
    }

    private void salvarEndereco(UsuarioEntity usuario) {
        EnderecoEntity endereco = usuario.getEndereco();

        usuario.setEndereco(enderecoRepository.save(
            EnderecoEntity
                .builder()
                .cep(endereco.getCep())
                .uf(endereco.getUf())
                .cidade(endereco.getCidade())
                .logradouro(endereco.getLogradouro())
                .complemento(endereco.getComplemento())
                .apelido(endereco.getApelido())
                .principal(endereco.getPrincipal())
                .build()
        ));
    }

    private void salvarFotoDePerfil(String temp, String path) {
        fileService.converteBytesParaArquivo(path, temp);
    }
    private void setProfiles(UsuarioEntity usuario) {
        List<ProfileEntity> profiles;

        if(usuario.getTipoPessoa() == TipoPessoaEnum.FISICA) {
             profiles = Collections.singletonList(profileRepository.findByNome("ROLE_PRESTADOR").get());

        } else {
            profiles = Collections.singletonList(profileRepository.findByNome("ROLE_IDOSO").get());
        }

        usuario.setProfileEntities(profiles);
    }
}
