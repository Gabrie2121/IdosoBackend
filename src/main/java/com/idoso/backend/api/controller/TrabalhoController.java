package com.idoso.backend.api.controller;

import com.idoso.backend.api.domain.entities.TrabalhoEntity;
import com.idoso.backend.api.domain.service.TrabalhoServiceImpl;
import com.idoso.backend.api.domain.service.contracts.TrabalhoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/prestador/")
public class TrabalhoController {

    private TrabalhoServiceImpl service;

    @GetMapping("trabalhos/{prestadorId}")
    public ResponseEntity<List<TrabalhoEntity>> findByPrestador(@PathVariable Long prestadorId){
        return ResponseEntity.ok(service.findByPrestador(prestadorId));
    }
}
