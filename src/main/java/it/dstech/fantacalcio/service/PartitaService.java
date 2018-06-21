package it.dstech.fantacalcio.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.dstech.fantacalcio.model.Campionato;
import it.dstech.fantacalcio.model.Giocatore;
import it.dstech.fantacalcio.model.Partita;
import it.dstech.fantacalcio.model.Squadra;
import it.dstech.fantacalcio.repository.IPartitaRepository;

@Service
public class PartitaService {

	@Autowired
	private IPartitaRepository dao;

	@Autowired
	private CampionatoService service;

	@Autowired
	private SquadraService serviceSquadra;

	//dataInizio e dataFine 
	//devo fare 38 partite 
	//se le squadre sono dispari, una squadra riposa.
	//sono due le squadre e in una settimana una squadra non puo giocare due volte
	//squadraUno (random) e squadraDue (random, meno squadraUno)
	//n partite, in base a quante squadre abbiamo nel DB

	public void primaPartita(Long squadraUno, Long squadraDue, Long idCampionato) throws Exception {
		Campionato campionato = service.findOne(idCampionato);
		List<Squadra> listaSquadre = campionato.getListaSquadre();
		Partita partita = new Partita();
		partita.setData(campionato.getDataInizio());
		for(int i = 0; i< 38; i++) {
			partita.setSquadraUno(serviceSquadra.findOne(squadraUno));
			partita.setSquadraDue(serviceSquadra.findOne(squadraDue));
			dao.save(partita);
		}
	}

	public Long risultatoPartita (Long idPartita) throws Exception {
		Partita partita = dao.findById(idPartita).orElseThrow(() -> new Exception());
		int punteggioGiocatoriSquadraUno = 0;
		int punteggioGiocatoriSquadraDue = 0;
		for (Giocatore giocatore : partita.getSquadraUno().getListaGiocatori()) {
			punteggioGiocatoriSquadraUno = (int) (punteggioGiocatoriSquadraUno + giocatore.getPunteggioDellaSettimana());
		}
		for (Giocatore giocatore : partita.getSquadraDue().getListaGiocatori()) {
			punteggioGiocatoriSquadraDue = (int) (punteggioGiocatoriSquadraDue + giocatore.getPunteggioDellaSettimana());
		}
		if(punteggioGiocatoriSquadraUno>punteggioGiocatoriSquadraDue) {
			return partita.getSquadraUno().getId();
		}else if(punteggioGiocatoriSquadraDue>punteggioGiocatoriSquadraUno) {
			return partita.getSquadraDue().getId();
		}else {
			return null;
		}
	}
}
