package it.dstech.fantacalcio.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

	/*
	public void primaPartita(Long squadraHome, Long squadraGuest, Long idCampionato) throws Exception {
		Campionato campionato = service.findOne(idCampionato);
		List<Squadra> listaSquadre = campionato.getListaSquadre();
		Partita partita = new Partita();
		partita.setData(campionato.getDataInizio());
		for(int i = 0; i< 38; i++) {
			partita.setIdSquadraHome(squadraHome);
			partita.setIdSquadraGuest(squadraGuest);
			dao.save(partita);
		}
	}
	*/
	
	public Partita scontriPartite(Long idCampionato , LocalDate dataPartita) throws Exception {
		Campionato campionato = service.findOne(idCampionato);
		List<Squadra> listaSquadre = campionato.getListaSquadre();
		Random sceltaSquadre = new Random();
		List<Long> listaIdSquadreHome = new ArrayList<>();
		List<Long> listaIdSquadreGuest = new ArrayList<>();
		Partita partita = new Partita();
		for (Squadra squadra : listaSquadre) {
			Long idSquadra = squadra.getId();
			listaIdSquadreHome.add(idSquadra);
		}
		for (Integer i = 0 ; i < 37 ; i++) {
			for (int j = 0; j < listaIdSquadreHome.size(); j++) {
				Long idSquadraHome = listaIdSquadreHome.get(sceltaSquadre.nextInt(listaIdSquadreHome.size()));
				for(int h = 0; h < listaIdSquadreGuest.size(); h++) {
					Long idSquadraGuest = listaIdSquadreGuest.get(sceltaSquadre.nextInt(listaIdSquadreGuest.size()));
					if(idSquadraHome != idSquadraGuest) {
						partita.setIdSquadraHome(idSquadraHome);
						partita.setIdSquadraGuest(idSquadraGuest);
						partita.setData(dataPartita);
						dao.save(partita);
					}
					listaIdSquadreGuest.remove(idSquadraGuest);
				}
				listaIdSquadreHome.remove(idSquadraHome);
			}
		}
		return partita;
	}
	
	public Partita prossimePartite(Long idCampionato , LocalDate dataPartita) throws Exception {
		Campionato campionato = service.findOne(idCampionato);
		List<Squadra> listaSquadre = campionato.getListaSquadre();
		Random sceltaSquadre = new Random();
		List<Long> listaIdSquadreHome = new ArrayList<>();
		List<Long> listaIdSquadreGuest = new ArrayList<>();
		Partita partita = new Partita();
		for (Squadra squadra : listaSquadre) {
			Long idSquadra = squadra.getId();
			listaIdSquadreHome.add(idSquadra);
		}
		for (Integer i = 0 ; i < 37 ; i++) {
			for (int j = 0; j < listaIdSquadreHome.size(); j++) {
				Long idSquadraHome = listaIdSquadreHome.get(sceltaSquadre.nextInt(listaIdSquadreHome.size()));
				for(int h = 0; h < listaIdSquadreGuest.size(); h++) {
					Long idSquadraGuest = listaIdSquadreGuest.get(sceltaSquadre.nextInt(listaIdSquadreGuest.size()));
					if(idSquadraHome != idSquadraGuest) {
						partita.setIdSquadraHome(idSquadraHome);
						partita.setIdSquadraGuest(idSquadraGuest);
						partita.setData(dataPartita);
						dao.save(partita);
					}
					listaIdSquadreGuest.remove(idSquadraGuest);
				}
				listaIdSquadreHome.remove(idSquadraHome);
			}
		}
		return partita;
	}
	
	public Squadra risultatoPartita (Long idPartita) throws Exception {
		Partita partita = dao.findById(idPartita).orElseThrow(() -> new Exception());
		Squadra squadraHome = serviceSquadra.findOne(partita.getIdSquadraHome());
		Squadra squadraGuest = serviceSquadra.findOne(partita.getIdSquadraGuest());
		int punteggioGiocatoriSquadraUno = 0;
		int punteggioGiocatoriSquadraDue = 0;
		for (Giocatore giocatore : squadraHome.getListaGiocatori()) {
			punteggioGiocatoriSquadraUno = (int) (punteggioGiocatoriSquadraUno + giocatore.getPunteggioDellaSettimana());
		}
		for (Giocatore giocatore : squadraGuest.getListaGiocatori()) {
			punteggioGiocatoriSquadraDue = (int) (punteggioGiocatoriSquadraDue + giocatore.getPunteggioDellaSettimana());
		}
		if(punteggioGiocatoriSquadraUno>punteggioGiocatoriSquadraDue) {
			squadraHome.setPunteggio(squadraHome.getPunteggio() + 3);
			serviceSquadra.salva(squadraHome);
			return squadraHome;
			
		}else if(punteggioGiocatoriSquadraDue>punteggioGiocatoriSquadraUno) {
			squadraGuest.setPunteggio(squadraGuest.getPunteggio() + 3);
			serviceSquadra.salva(squadraGuest);
			return squadraGuest;
		}else {
			squadraHome.setPunteggio(squadraHome.getPunteggio() + 1);
			serviceSquadra.salva(squadraHome);
			
			squadraGuest.setPunteggio(squadraGuest.getPunteggio() + 1);
			serviceSquadra.salva(squadraGuest);
			System.out.println("Pareggio");
			return null;
		}
	}
}
