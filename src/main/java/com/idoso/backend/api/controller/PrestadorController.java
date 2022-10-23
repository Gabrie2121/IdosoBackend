package com.idoso.backend.api.controller;

import com.idoso.backend.api.domain.dto.response.CandidaturaCriadaDTO;
import com.idoso.backend.api.domain.entities.CandidaturaPrestadorEntity;
import com.idoso.backend.api.service.CandidaturaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prestadores")
@RequiredArgsConstructor
@CrossOrigin
public class PrestadorController {

    private final CandidaturaService candidaturaService;

    @PostMapping("/candidatura/nova")
    private ResponseEntity<CandidaturaCriadaDTO> novaCandiatura(@RequestBody CandidaturaPrestadorEntity candidaturaData) {
        CandidaturaCriadaDTO candidatura = candidaturaService.novaCandidatura(candidaturaData);

        return ResponseEntity.ok(candidatura);
    }
}
