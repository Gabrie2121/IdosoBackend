package original.controller;

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

import original.model.CadastroPF;
import original.repository.CadastroPFRepository;

@RestController("/cadastroPF")
public class CadastroPFController {

	@Autowired
	private CadastroPFRepository cadastroPFRepository;
	
	@GetMapping("/all")
	public List<CadastroPF> getAll(){
		return cadastroPFRepository.findAll();
	}
	
	@PostMapping("/post")
	public CadastroPF post(@Validated @RequestBody CadastroPF cadastroPF) {
		return cadastroPFRepository.save(cadastroPF);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<CadastroPF> delete(@PathVariable(value="id") long id){
		Optional<CadastroPF> cadastroPF = cadastroPFRepository.findById(id);
		if(cadastroPF.isPresent()) {
			cadastroPFRepository.delete(cadastroPF.get());
			return new ResponseEntity<CadastroPF>(HttpStatus.OK);
		}else {
			return new ResponseEntity<CadastroPF>(HttpStatus.NOT_FOUND);
		}
		
	}
}
