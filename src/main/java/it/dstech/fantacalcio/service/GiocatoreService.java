package it.dstech.fantacalcio.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import it.dstech.fantacalcio.model.Campionato;
import it.dstech.fantacalcio.model.Giocatore;
import it.dstech.fantacalcio.model.Ruolo;
import it.dstech.fantacalcio.model.Squadra;
import it.dstech.fantacalcio.model.User;
import it.dstech.fantacalcio.repository.IGiocatoreRepository;

@Service
public class GiocatoreService {

	@Autowired
	private UserService service;

	@Autowired
	private IGiocatoreRepository dao;

	@Autowired
	private CampionatoService serviceCampionato;


	public Giocatore createOneADMIN(Giocatore giocatore, Long idCampionato) throws Exception {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = service.findByUsername(auth.getName());

		Campionato campionato = serviceCampionato.findOne(idCampionato);
		List<Giocatore> listaGiocatoriDisp = campionato.getListaGiocatoriDisponibili();
		boolean flag = false;
		if(listaGiocatoriDisp==null) listaGiocatoriDisp = new ArrayList<>();
		if(!listaGiocatoriDisp.contains(giocatore)) {
			flag = true;
			listaGiocatoriDisp.add(giocatore);
		}
		if(flag==false) {
			throw new Exception ("Questo giocatore è già presente");
		}
		campionato.setListaGiocatoriDisponibili(listaGiocatoriDisp);
		serviceCampionato.create(campionato);
		return dao.save(giocatore);
	}

	public Iterable<Giocatore> createListaADMIN(ArrayList<Giocatore> listaGiocatori, Long idCampionato) throws Exception{

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = service.findByUsername(auth.getName());

		Campionato campionato = serviceCampionato.findOne(idCampionato);
		List<Giocatore> listaGiocatoriDisp = campionato.getListaGiocatoriDisponibili();
		if(listaGiocatoriDisp==null) listaGiocatoriDisp = new ArrayList<>();
		boolean flag = false;
		for(Giocatore giocatore : listaGiocatori) {
			if(!listaGiocatoriDisp.contains(giocatore)) {
				flag = true;
				giocatore.setCampionato(campionato);
				listaGiocatoriDisp.add(giocatore);
			}
		}
		if(flag==false) {
			throw new Exception ("Questo giocatore è già presente");
		}
		campionato.setListaGiocatoriDisponibili(listaGiocatoriDisp);
		serviceCampionato.create(campionato);
		return dao.saveAll(listaGiocatori);
	}

	public void deleteOne(Long id) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = service.findByUsername(auth.getName());

		dao.deleteById(id);
	}

	public void deleteAll() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = service.findByUsername(auth.getName());

		dao.deleteAll();
	}

	public Giocatore findOne(Long id) throws Exception {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = service.findByUsername(auth.getName());

		return dao.findById(id).orElseThrow(() -> new Exception());
	}

	public Iterable<Giocatore> findAll(){

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = service.findByUsername(auth.getName());

		return dao.findAll();
	}

	public Giocatore update(Giocatore giocatoreInput, Long id) throws Exception {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = service.findByUsername(auth.getName());

		Giocatore giocatoreDB = findOne(id);
		giocatoreDB.setNome(giocatoreInput.getNome());
		giocatoreDB.setCognome(giocatoreInput.getCognome());
		giocatoreDB.setNazione(giocatoreInput.getNazione());
		giocatoreDB.setRuolo(giocatoreInput.getRuolo());
		giocatoreDB.setPrezzo(giocatoreInput.getPrezzo());
		giocatoreDB.setPunteggioDellaSettimana(giocatoreInput.getPunteggioDellaSettimana());
		giocatoreDB.setTitolare(giocatoreInput.isTitolare());
		return dao.save(giocatoreDB);
	}
	
	//solo admin può fare questo
	public Giocatore aggiornaPunteggioGiocatore(Long idGiocatore, Long nuovoPunteggioSettimana) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = service.findByUsername(auth.getName());

		Giocatore giocatoreDB = findOne(idGiocatore);
		giocatoreDB.setPunteggioDellaSettimana(nuovoPunteggioSettimana);
		
		return dao.save(giocatoreDB);
	}
	
	public List<Giocatore> compraGiocatore (Long idGiocatore) throws Exception {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = service.findByUsername(auth.getName());

		Squadra squadra = user.getSquadra();
		List<Giocatore> listaDisponibili= squadra.getCampionato().getListaGiocatoriDisponibili();
		List<Giocatore> listaGiocatoriSquadra = squadra.getListaGiocatori();
		Giocatore giocatoreTemp = null;
		if (listaGiocatoriSquadra == null) listaGiocatoriSquadra = new ArrayList<>();
		int counterIf = 0;
		for (Giocatore giocatore : listaDisponibili) {
			counterIf=0;
			if(listaGiocatoriSquadra.size()<24 && !listaGiocatoriSquadra.contains(giocatore)) {
				counterIf++;
				if (giocatore.getId().equals(idGiocatore)) {
					counterIf++;
					if(controlloRuolo(Ruolo.ROLE_PORTIERE) <= 3 || controlloRuolo(Ruolo.ROLE_DIFENSORE) <= 8 || controlloRuolo(Ruolo.ROLE_CENTROCAMPISTA) <= 8 || controlloRuolo(Ruolo.ROLE_ATTACCANTE) <= 6){
						counterIf++;
						Integer prezzoGiocatore = giocatore.getPrezzo();
						if(user.getCreditoDaSpendere() >= prezzoGiocatore) {
							counterIf++;
							listaGiocatoriSquadra.add(giocatore);
							user.setCreditoDaSpendere(user.getCreditoDaSpendere()-prezzoGiocatore);
							squadra.setListaGiocatori(listaGiocatoriSquadra);
							giocatore.setSquadra(squadra);
							giocatoreTemp = giocatore;
							break;
						}
					}
				}
			}	
		}
		if (counterIf == 4) {
			dao.save(giocatoreTemp);
			return listaGiocatoriSquadra;
		} else {
			throw new Exception("Errore");
		}
	}

	public Integer controlloRuolo(Ruolo ruolo) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = service.findByUsername(auth.getName());

		Squadra squadra = user.getSquadra();
		List<Giocatore> listaGiocatoriSquadra = squadra.getListaGiocatori();
		Integer numeroGiocatoriPerRuolo = 0;
		for (Giocatore giocatore : listaGiocatoriSquadra) {
			if(giocatore.getRuolo().equals(ruolo) ) {
				numeroGiocatoriPerRuolo++;
			}
		}

		return numeroGiocatoriPerRuolo;
	}

}
