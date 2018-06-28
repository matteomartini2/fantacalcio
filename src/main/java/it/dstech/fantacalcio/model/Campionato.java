package it.dstech.fantacalcio.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "campionato")
public class Campionato extends Base{

	@Column(name="nome_campionato",nullable = false)
	private String nomeCampionato;

	@Column(name="nazione_campionato",nullable = false)	
	private String nazioneCampionato;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy= "campionato")
	@JsonIgnore
	private List<Squadra> listaSquadre;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy= "campionato")
	@JsonIgnore
	private List<Giocatore> listaGiocatoriDisponibili;

	@Column(name = "data_inizio", unique = false, nullable = false)
	private LocalDate dataInizio;

	@OneToMany(fetch =FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "campionato")
	@JsonIgnore
	private List<Partita> listaPartite;
	
	

	//getters and setters


	public List<Partita> getListaPartite() {
		return listaPartite;
	}

	public void setListaPartite(List<Partita> listaPartite) {
		this.listaPartite = listaPartite;
	}

	public LocalDate getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(LocalDate dataInizio) {
		this.dataInizio = dataInizio;
	}

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
