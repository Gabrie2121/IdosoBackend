package com.idoso.backend.api.controller.contracts;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.idoso.backend.api.domain.dto.request.AnuncioDTO;
import com.idoso.backend.api.domain.exception.ObjectNotFoundException;

public interface AnuncioController {

    ResponseEntity<AnuncioDTO> findById(@PathVariable Long id) throws ObjectNotFoundException;

    ResponseEntity<List<AnuncioDTO>> findAll();

}
