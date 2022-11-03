package com.idoso.backend.api.controller;

import com.idoso.backend.api.domain.entities.EnderecoEntity;
import com.idoso.backend.api.domain.entities.UsuarioEntity;
import com.idoso.backend.api.domain.exception.UserNotFoundException;
import com.idoso.backend.api.domain.repository.EnderecoRepository;
import com.idoso.backend.api.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/enderecos")
@RequiredArgsConstructor
public class EnderecoController {

    private final EnderecoRepository enderecoRepository;

    private final UsuarioRepository usuarioRepository;

    @GetMapping("findByUsuario/{idUsuario}")
    public ResponseEntity<EnderecoEntity> findByUsuario(@PathVariable("idUsuario") String idUsuario) throws UserNotFoundException {
        UsuarioEntity usuario = usuarioRepository.findById(Long.parseLong(idUsuario))
                .orElseThrow(() -> new UserNotFoundException("Usuario inexistente"));

        return ResponseEntity.ok(enderecoRepository.findByUsuario(usuario).get());
    }
}
