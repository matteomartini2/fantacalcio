package it.dstech.fantacalcio.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "partita")
public class Partita extends Base {

	@Column (name = "data", unique = false, nullable = false)
	private LocalDate data;

	@Column (name = "id_squadra_home", unique = false, nullable = false)
	private Long idSquadraHome;

	@Column (name = "id_squadra_guest", unique = false, nullable = false)
	private Long idSquadraGuest;




	public Long getIdSquadraHome() {
		return idSquadraHome;
	}

	public void setIdSquadraHome(Long idSquadraHome) {
		this.idSquadraHome = idSquadraHome;
	}

	public Long getIdSquadraGuest() {
		return idSquadraGuest;
	}

	public void setIdSquadraGuest(Long idSquadraGuest) {
		this.idSquadraGuest = idSquadraGuest;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
	
	
}
