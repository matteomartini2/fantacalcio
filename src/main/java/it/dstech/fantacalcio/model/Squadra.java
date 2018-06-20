package it.dstech.fantacalcio.model;



import java.util.List;

import javax.persistence.Entity;

@Entity
public class Squadra  extends Base{
	
	private String nome;
	private List<Giocatore> listaGiocatori;
	private Modulo modulo;
	private Integer punteggio;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
