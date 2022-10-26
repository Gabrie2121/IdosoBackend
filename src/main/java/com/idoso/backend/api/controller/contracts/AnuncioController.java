package com.idoso.backend.api.controller.contracts;

import com.idoso.backend.api.domain.dto.response.AnuncioCriadoDTO;
import com.idoso.backend.api.domain.entities.AnuncioEntity;
import com.idoso.backend.api.domain.exception.ObjectNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface AnuncioController {

    ResponseEntity<AnuncioEntity> findById(@PathVariable Long id) throws ObjectNotFoundException;

    public ResponseEntity<List<AnuncioCriadoDTO>> findAll();

}
