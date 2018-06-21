package it.dstech.fantacalcio.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "partita")
public class Partita extends Base {

	@Column (name = "data", unique = false, nullable = false)
	private LocalDate data;

	@Column (name = "squadraUno", unique = false, nullable = false)
	private Long idSquadraUno;
	
	@Column (name = "squadraDue", unique = false, nullable = false)
	private Long idSquadraDue;

	
	

	public Long getIdSquadraUno() {
		return idSquadraUno;
	}

	public void setIdSquadraUno(Long idSquadraUno) {
		this.idSquadraUno = idSquadraUno;
	}

	public Long getIdSquadraDue() {
		return idSquadraDue;
	}

	public void setIdSquadraDue(Long idSquadraDue) {
		this.idSquadraDue = idSquadraDue;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
	
	
}
