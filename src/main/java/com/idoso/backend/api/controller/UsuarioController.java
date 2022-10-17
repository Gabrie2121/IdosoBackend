package com.idoso.backend.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;


import com.idoso.backend.api.domain.entities.UsuarioEntity;
import com.idoso.backend.api.domain.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
@CrossOrigin
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	

	@GetMapping("/all")
	public List<UsuarioEntity> getAll(){
		return usuarioRepository.findAll();
	}
	
	@PostMapping("/post")
	public UsuarioEntity post(@Validated @RequestBody UsuarioEntity usuario) {
		return usuarioRepository.save(usuario);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<UsuarioEntity> delete(@PathVariable(value="id") long id){
		Optional<UsuarioEntity> cadastroPF = usuarioRepository.findById(id);
		if(cadastroPF.isPresent()) {
			usuarioRepository.delete(cadastroPF.get());
			return new ResponseEntity<UsuarioEntity>(HttpStatus.OK);
		}else {
			return new ResponseEntity<UsuarioEntity>(HttpStatus.NOT_FOUND);
		}
		
	}
}

