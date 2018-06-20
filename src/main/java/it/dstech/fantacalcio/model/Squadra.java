package it.dstech.fantacalcio.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="squadra")
public class Squadra extends Base {

	@Column (name = "nome", unique = false, nullable = false)
	private String nome;
	
	@Column (name = "lista_giocatori", unique = false, nullable = false)
	private List<Giocatore> listaGiocatori;
	
	private Modulo modulo;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn
	@JsonIgnore
	private User user;
	
	@Column (name = "punteggio", unique = false, nullable = false)
	private Integer punteggio;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Giocatore> getListaGiocatori() {
		return listaGiocatori;
	}

	public void setListaGiocatori(List<Giocatore> listaGiocatori) {
		this.listaGiocatori = listaGiocatori;
	}

	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	public Integer getPunteggio() {
		return punteggio;
	}

	public void setPunteggio(Integer punteggio) {
		this.punteggio = punteggio;
	}

}