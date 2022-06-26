package com.IDoso.Projeto.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.IDoso.Projeto.model.Login;
import com.IDoso.Projeto.repository.LoginRepository;


@RestController("/login")
public class LoginController {
	
	@Autowired
	private LoginRepository loginRepository;
	
	@GetMapping("/all")
	public List<Login> getAll(){
		return loginRepository.findAll();
	}
	
	@PostMapping("/post")
	public Login post(@Validated @RequestBody Login login) {
		return loginRepository.save(login);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Login> delete(@PathVariable(value="id") long id){
		Optional<Login> login = loginRepository.findById(id);
		if(login.isPresent()) {
			loginRepository.delete(login.get());
			return new ResponseEntity<Login>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Login>(HttpStatus.NOT_FOUND);
		}
		
	}
}
