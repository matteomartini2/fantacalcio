package it.dstech.fantacalcio.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="squadra")
public class Squadra extends Base {

	@Column (name = "nome", unique = false, nullable = false)
	private String nome;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy="squadra")
	private List<Giocatore> listaGiocatori;
	
	@Enumerated(EnumType.STRING)
	private Modulo modulo;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn
	@JsonIgnore
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Campionato campionato;
	
	@Column (name = "punteggio", unique = false, nullable = false)
	private Integer punteggio = 0;
	
	@Column (name = "data_registrazione", unique = false, nullable = false)
	private final LocalDate dataRegistrazione = LocalDate.now();
	
	/*
	public LocalDate getDataRegistrazione() {
		return dataRegistrazione;
	}

	public void setDataRegistrazione(LocalDate dataRegistrazione) {
		this.dataRegistrazione = dataRegistrazione;
	}
	*/
	
	
	public String getNome() {
		return nome;
	}



	public Integer getPunteggio() {
		return punteggio;
	}



	public void setPunteggio(Integer punteggio) {
		this.punteggio = punteggio;
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

	public Campionato getCampionato() {
		return campionato;
	}

	public void setCampionato(Campionato campionato) {
		this.campionato = campionato;
	}
	
	

}
