package it.dstech.fantacalcio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "giocatore")
public class Giocatore extends Base{

	@Column (name = "nome", unique = false, nullable = false)
	private String nome;
	
	@Column (name = "cognome", unique = false, nullable = false)
	private String cognome;
	
	@Column (name = "nazione", unique = false, nullable = false)
	private String nazione;
	
	@Column (name = "punteggio_della_settimana", unique = false, nullable = false)
	private Long punteggioDellaSettimana;
	
	@Enumerated(EnumType.STRING)
	private Ruolo ruolo;
	
	@Column (name = "prezzo", unique = false, nullable = false)
	private Integer prezzo;
	
	@Column (name = "titolare", unique = false, nullable = false)
	private boolean titolare = false;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	@JsonIgnore
	private Campionato campionato;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	@JsonIgnore
	private Squadra squadra;
	
	//getters and setters
	
	
	
	public String getNome() {
		return nome;
	}
	public Campionato getCampionato() {
		return campionato;
	}
	public void setCampionato(Campionato campionato) {
		this.campionato = campionato;
	}
	public Squadra getSquadra() {
		return squadra;
	}
	public void setSquadra(Squadra squadra) {
		this.squadra = squadra;
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
	public String getNazione() {
		return nazione;
	}
	public void setNazione(String nazione) {
		this.nazione = nazione;
	}
	public Long getPunteggioDellaSettimana() {
		return punteggioDellaSettimana;
	}
	public void setPunteggioDellaSettimana(Long punteggioDellaSettimana) {
		this.punteggioDellaSettimana = punteggioDellaSettimana;
	}
	public Ruolo getRuolo() {
		return ruolo;
	}
	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}
	public Integer getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(Integer prezzo) {
		this.prezzo = prezzo;
	}
	public boolean isTitolare() {
		return titolare;
	}
	public void setTitolare(boolean titolare) {
		this.titolare = titolare;
	}
	
	
}
