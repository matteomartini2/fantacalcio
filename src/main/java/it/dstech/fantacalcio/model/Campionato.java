package it.dstech.fantacalcio.model;

import java.util.List;

import javax.persistence.Entity;

@Entity(name= "campionato")
public class Campionato {

	
	private String nome;
	
	private String nazione;
	
	private List<Squadra> listaSquadre;
	
	
	private List<Giocatore> listaGiocatoriDisponibili;

	

	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getNazione() {
		return nazione;
	}


	public void setNazione(String nazione) {
		this.nazione = nazione;
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
