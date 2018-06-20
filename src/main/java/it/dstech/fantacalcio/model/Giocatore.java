package it.dstech.fantacalcio.model;

import javax.persistence.Column;
import javax.persistence.Entity;

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
	
	@Column (name = "ruolo", unique = false, nullable = false)
	private Ruolo ruolo;
	
	@Column (name = "prezzo", unique = false, nullable = false)
	private Integer prezzo;
	
	@Column (name = "titolare", unique = false, nullable = false)
	private boolean titolare;
	
	
	//getters and setters
	
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
