package com.idoso.backend.api.controller;

import com.idoso.backend.api.domain.dto.response.HomeUsuarioDTO;
import com.idoso.backend.api.domain.entities.UsuarioEntity;
import com.idoso.backend.api.domain.repository.UsuarioRepository;
import com.idoso.backend.api.domain.service.contracts.IdosoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/idoso")
@RequiredArgsConstructor
@CrossOrigin
public class UsuarioController {

	private final UsuarioRepository usuarioRepository;

	private final IdosoService idosoService;


	@GetMapping("/all")
	public List<UsuarioEntity> getAll(){
		return usuarioRepository.findAll();
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<UsuarioEntity> delete(@PathVariable(value="id") long id){
		Optional<UsuarioEntity> cadastroPF = usuarioRepository.findById(id);
		if(cadastroPF.isPresent()) {
			usuarioRepository.delete(cadastroPF.get());
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}




	@GetMapping("/getById/{idUsuario}")
	public ResponseEntity<UsuarioEntity> getById (@PathVariable("idUsuario") String idUsuario) {
		UsuarioEntity usuario = usuarioRepository.findById(Long.parseLong(idUsuario))
				.orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado id "+ idUsuario ));

		return ResponseEntity.ok(usuario);
	}

	@PatchMapping("/atualizaBiografia")
	public String atualizarBioagrafia(@RequestBody UsuarioEntity usuario) {
		Long id = usuario.getId();

		UsuarioEntity bdUsuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado id "+ id));

		bdUsuario.setBiografia(usuario.getBiografia());

		usuarioRepository.save(bdUsuario);

		return "Biografia modificada com sucesso";

	}

	@GetMapping("/home/{idUsuario}")
	public ResponseEntity<HomeUsuarioDTO> getHome(@PathVariable String idUsuario) {
		HomeUsuarioDTO homeData = idosoService.getHome(Long.parseLong(idUsuario));
		return ResponseEntity.ok(homeData);
	}

}

