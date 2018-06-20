package it.dstech.fantacalcio.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name= "campionato")
public class Campionato {

	@Column(name="nome_campionato",nullable = false)
	private String nomeCampionato;
	
	@Column(name="nazione_campionato",nullable = false)	
	private String nazioneCampionato;
	
	@OneToMany
	@JsonIgnore
	private List<Squadra> listaSquadre;
	
	@OneToMany
	@JsonIgnore
	private List<Giocatore> listaGiocatoriDisponibili;

	
	
	public String getNomeCampionato() {
		return nomeCampionato;
	}

	public void setNomeCampionato(String nomeCampionato) {
		this.nomeCampionato = nomeCampionato;
	}

	public String getNazioneCampionato() {
		return nazioneCampionato;
	}

	public void setNazioneCampionato(String nazioneCampionato) {
		this.nazioneCampionato = nazioneCampionato;
	}

	public List<Squadra> getListaSquadre() {
		return listaSquadre;
	}

	public void setListaSquadre(List<Squadra> listaSquadre) {
		this.listaSquadre = listaSquadre;
	}

	public List<Giocatore> getListaGiocatoriDisponibili() {
		return listaGiocatoriDisponibili;
	}

	public void setListaGiocatoriDisponibili(List<Giocatore> listaGiocatoriDisponibili) {
		this.listaGiocatoriDisponibili = listaGiocatoriDisponibili;
	}

	

	
	
}
