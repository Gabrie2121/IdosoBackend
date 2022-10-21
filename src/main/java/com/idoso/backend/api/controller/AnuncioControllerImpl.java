package com.idoso.backend.api.controller;

import com.idoso.backend.api.controller.contracts.AnuncioController;
import com.idoso.backend.api.domain.dto.request.AnuncioDTO;
import com.idoso.backend.api.domain.entities.AnuncioEntity;
import com.idoso.backend.api.domain.exception.ObjectNotFoundException;
import com.idoso.backend.api.domain.service.AnuncioServiceImpl;
import com.idoso.backend.api.domain.service.contracts.AnuncioService;
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

    @GetMapping()
    public ResponseEntity<List<AnuncioEntity>> findAll() {
        return null;
    }


    @PostMapping("novo")
    public ResponseEntity<AnuncioEntity> criarAnuncio(@RequestBody AnuncioDTO request) {
        return ResponseEntity.ok(service.criarAnuncio(request));
    }

}
