package com.idoso.backend.api.controller;

import com.idoso.backend.api.domain.CandidaturaDTO;
import com.idoso.backend.api.domain.service.contracts.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prestador")
@RequiredArgsConstructor
@CrossOrigin
public class PrestadorController {

    private final UsuarioService usuarioService;
    @PostMapping("/candidatar")
    public ResponseEntity<?> candidatura(@RequestBody CandidaturaDTO dto) {
        return ResponseEntity.ok(usuarioService.novaCandidatura(dto));
    }


}
