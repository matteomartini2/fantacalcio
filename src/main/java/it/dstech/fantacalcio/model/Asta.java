package it.dstech.fantacalcio.model;

import java.time.LocalDateTime;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;

@Entity
public class Asta extends Base{
	
	@JoinColumn(name="data_inizio")
	LocalDateTime dataInizio;
	@JoinColumn(name="data_fine")
	LocalDateTime dataFine;
	
	@Column(name="giocatore_id")
	Long idGiocatore;
	
	@JoinColumn
	Map<Long,Double> offerte;

	public LocalDateTime getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(LocalDateTime dataInizio) {
		this.dataInizio = dataInizio;
	}

	public LocalDateTime getDataFine() {
		return dataFine;
	}

	public void setDataFine(LocalDateTime dataFine) {
		this.dataFine = dataFine;
	}

	public Long getIdGiocatore() {
		return idGiocatore;
	}

	public void setIdGiocatore(Long idGiocatore) {
		this.idGiocatore = idGiocatore;
	}

	public Map<Long, Double> getOfferte() {
		return offerte;
	}

	public void setOfferte(Map<Long, Double> offerte) {
		this.offerte = offerte;
	}

	
	
	
	
}
