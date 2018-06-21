package it.dstech.fantacalcio.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "partita")
public class Partita {

	@Column (name = "data", unique = false, nullable = false)
	private LocalDate data;

	@Column (name = "squadraUno", unique = false, nullable = false)
	private Squadra squadraUno;
	
	@Column (name = "squadraDue", unique = false, nullable = false)
	private Squadra squadraDue;

	
	public Squadra getSquadraUno() {
		return squadraUno;
	}

	public void setSquadraUno(Squadra squadraUno) {
		this.squadraUno = squadraUno;
	}

	public Squadra getSquadraDue() {
		return squadraDue;
	}

	public void setSquadraDue(Squadra squadraDue) {
		this.squadraDue = squadraDue;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
	
	
}
