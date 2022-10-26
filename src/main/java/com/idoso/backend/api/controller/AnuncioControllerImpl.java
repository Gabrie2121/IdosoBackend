package com.idoso.backend.api.controller;

import com.idoso.backend.api.controller.contracts.AnuncioController;
import com.idoso.backend.api.domain.dto.request.AnuncioUsuarioDTO;
import com.idoso.backend.api.domain.dto.response.AnuncioCriadoDTO;
import com.idoso.backend.api.domain.entities.AnuncioEntity;
import com.idoso.backend.api.domain.exception.ObjectNotFoundException;
import com.idoso.backend.api.domain.service.AnuncioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/anuncios")
@RequiredArgsConstructor
public class AnuncioControllerImpl implements AnuncioController {
    private final AnuncioServiceImpl service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<AnuncioEntity> findById(@PathVariable Long id) throws ObjectNotFoundException {
        return null;

    }

    @PostMapping("/novo")
    public ResponseEntity<AnuncioCriadoDTO> criarAnuncio(@RequestBody AnuncioUsuarioDTO request) {
        AnuncioEntity anunciodb = service.criarAnuncio(request);
        return ResponseEntity.ok(AnuncioCriadoDTO
                .builder()
                    .nomeIdoso(anunciodb.getIdoso().getNome())
                    .id(anunciodb.getId())
                .build());
    }


    @GetMapping("/all")
    public ResponseEntity<List<AnuncioCriadoDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }



}
