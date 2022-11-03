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

import original.model.CadastroPJ;
import original.repository.CadastroPJRepository;

@RestController("/cadastroPF")
public class CadastroPJController {

	@Autowired
	private CadastroPJRepository cadastroPJRepository;
	
	@GetMapping("/all")
	public List<CadastroPJ> getAll(){
		return cadastroPJRepository.findAll();
	}
	
	@PostMapping("/post")
	public CadastroPJ post(@Validated @RequestBody CadastroPJ cadastroPJ) {
		return cadastroPJRepository.save(cadastroPJ);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<CadastroPJ> delete(@PathVariable(value="id") long id){
		Optional<CadastroPJ> cadastroPJ = cadastroPJRepository.findById(id);
		if(cadastroPJ.isPresent()) {
			cadastroPJRepository.delete(cadastroPJ.get());
			return new ResponseEntity<CadastroPJ>(HttpStatus.OK);
		}else {
			return new ResponseEntity<CadastroPJ>(HttpStatus.NOT_FOUND);
		}
		
	}
}
