package original.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Login {
	@Id
	@GeneratedValue
	Long id;
	
	String email;
	
	String senha;
	
	CadastroPF cadastroPF;
	
	
	CadastroPJ cadastroPJ;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public CadastroPF getCadastroPF() {
		return cadastroPF;
	}


	public void setCadastroPF(CadastroPF cadastroPF) {
		this.cadastroPF = cadastroPF;
	}


	public CadastroPJ getCadastroPJ() {
		return cadastroPJ;
	}


	public void setCadastroPJ(CadastroPJ cadastroPJ) {
		this.cadastroPJ = cadastroPJ;
	}
	
	
}
