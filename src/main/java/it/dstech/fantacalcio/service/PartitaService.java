package it.dstech.fantacalcio.service;

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
			partita.setIdSquadraUno(squadraUno);
			partita.setIdSquadraDue(squadraDue);
			dao.save(partita);
		}
	}

	public Squadra risultatoPartita (Long idPartita) throws Exception {
		Partita partita = dao.findById(idPartita).orElseThrow(() -> new Exception());
		Squadra squadraUno = serviceSquadra.findOne(partita.getIdSquadraUno());
		Squadra squadraDue = serviceSquadra.findOne(partita.getIdSquadraDue());
		int punteggioGiocatoriSquadraUno = 0;
		int punteggioGiocatoriSquadraDue = 0;
		for (Giocatore giocatore : squadraUno.getListaGiocatori()) {
			punteggioGiocatoriSquadraUno = (int) (punteggioGiocatoriSquadraUno + giocatore.getPunteggioDellaSettimana());
		}
		for (Giocatore giocatore : squadraDue.getListaGiocatori()) {
			punteggioGiocatoriSquadraDue = (int) (punteggioGiocatoriSquadraDue + giocatore.getPunteggioDellaSettimana());
		}
		if(punteggioGiocatoriSquadraUno>punteggioGiocatoriSquadraDue) {
			squadraUno.setPunteggio(squadraUno.getPunteggio() + 3);
			serviceSquadra.salva(squadraUno);
			return squadraUno;
			
		}else if(punteggioGiocatoriSquadraDue>punteggioGiocatoriSquadraUno) {
			squadraDue.setPunteggio(squadraDue.getPunteggio() + 3);
			serviceSquadra.salva(squadraDue);
			return squadraDue;
		}else {
			squadraUno.setPunteggio(squadraUno.getPunteggio() + 1);
			serviceSquadra.salva(squadraUno);
			
			squadraDue.setPunteggio(squadraDue.getPunteggio() + 1);
			serviceSquadra.salva(squadraDue);
			System.out.println("Pareggio");
			return null;
		}
	}
}
