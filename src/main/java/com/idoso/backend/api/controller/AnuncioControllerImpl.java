package com.idoso.backend.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.idoso.backend.api.domain.entities.AnuncioEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.idoso.backend.api.controller.contracts.AnuncioController;

import com.idoso.backend.api.domain.dto.request.AnuncioDTO;
import com.idoso.backend.api.domain.exception.ObjectNotFoundException;
import com.idoso.backend.api.domain.service.contracts.AnuncioService;

@RestController
@RequestMapping(value = "/cuidador/anuncios")
@RequiredArgsConstructor
public class AnuncioControllerImpl implements AnuncioController {
    private final AnuncioService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<AnuncioDTO> findById(@PathVariable Long id) throws ObjectNotFoundException {
        AnuncioDTO objDTO = new AnuncioDTO(service.findById(id));
        return ResponseEntity.ok().body(objDTO);

    }

    @GetMapping()
    public ResponseEntity<List<AnuncioDTO>> findAll() {
        List<AnuncioDTO> listDTO = service.findAll().stream().map(obj -> new AnuncioDTO(obj))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(listDTO);
    }




}
