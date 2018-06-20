package it.dstech.fantacalcio.model;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity(name = "user")
public class User  extends Base{

	
	@Column (name = "nome", nullable = false, unique = true)
	private String nome;
	
	@Column (name = "cognome", nullable = false, unique = true)
	private String cognome;
	

	@Column (name = "username", nullable = false, unique = true)
	private String username;
	
	@Column (name = "password", nullable = false, unique = true)
	private String password;
	
	@Column (name = "credito_da_spendere", nullable = false, unique = true)
	private Integer creditoDaSpendere;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getCreditoDaSpendere() {
		return creditoDaSpendere;
	}

	public void setCreditoDaSpendere(Integer creditoDaSpendere) {
		this.creditoDaSpendere = creditoDaSpendere;
	}

	
}
