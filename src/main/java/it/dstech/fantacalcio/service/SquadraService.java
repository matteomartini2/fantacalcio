package it.dstech.fantacalcio.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import it.dstech.fantacalcio.model.Campionato;
import it.dstech.fantacalcio.model.Giocatore;
import it.dstech.fantacalcio.model.Modulo;
import it.dstech.fantacalcio.model.Ruolo;
import it.dstech.fantacalcio.model.Squadra;
import it.dstech.fantacalcio.model.User;
import it.dstech.fantacalcio.repository.ISquadraRepository;

@Service
public class SquadraService {

	@Autowired
	private ISquadraRepository dao;

	@Autowired
	private UserService serviceUser;

	@Autowired
	private CampionatoService campionatoService;
	
	@Autowired
	private GiocatoreService giocatoreService;

	public Iterable<Squadra> findAll() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = serviceUser.findByUsername(auth.getName());
		return dao.findAll();

	}

	public Squadra findOne(Long id) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = serviceUser.findByUsername(auth.getName());
		Squadra squadra = dao.findById(id).orElseThrow(() -> new Exception());
		return squadra;
	}

	public void deleteOne(Long id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = serviceUser.findByUsername(auth.getName());
		dao.deleteById(id);
	}

	public void deleteAll() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = serviceUser.findByUsername(auth.getName());
		dao.deleteAll();
	}

	public Squadra create(Squadra squadra, Long idCampionato) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = serviceUser.findByUsername(auth.getName());
		Campionato campionato = campionatoService.findOne(idCampionato);
		LocalDate dataOggi = LocalDate.now();
		if(dataOggi.isAfter(campionato.getDataInizio())) {
			throw new Exception ("Campionato non disponibile.");
		}
		if(user.getSquadra()==null && !campionato.getListaSquadre().contains(squadra)) {
			campionato.getListaSquadre().add(squadra);
			squadra.setCampionato(campionato);
			squadra.setUser(user);
			return dao.save(squadra);
		}else {
			return user.getSquadra();
		}
	}

	public Squadra update(Squadra s, Long idCampionato) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = serviceUser.findByUsername(auth.getName());
		Campionato campionato = campionatoService.findOne(idCampionato);
		LocalDate dataOggi = LocalDate.now();
		if(dataOggi.isAfter(campionato.getDataInizio())) {
			throw new Exception ("Campionato non disponibile.");
		}
		Squadra squadra = dao.findById(s.getId()).get();
		squadra.setListaGiocatori(s.getListaGiocatori());
		squadra.setModulo(s.getModulo());
		squadra.setNome(s.getNome());
		squadra.setPunteggio(s.getPunteggio());
		squadra.setUser(s.getUser());
		squadra.setDataRegistrazione(s.getDataRegistrazione());
		campionato.getListaSquadre().add(s);
		return dao.save(squadra);

	}

	public Squadra associaSquadraCampionato(Squadra squadra, String NomeCampionato) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = serviceUser.findByUsername(auth.getName());

		Campionato campionato = campionatoService.findByUsername(NomeCampionato);

		List<Squadra> listaSquadre = campionato.getListaSquadre();
		if (listaSquadre == null)
			listaSquadre = new ArrayList<>();
		listaSquadre.add(squadra);
		campionato.setListaSquadre(listaSquadre);
		return dao.save(squadra);

	}

	public Squadra findByNome(String nome) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = serviceUser.findByUsername(auth.getName());

		return dao.findBynome(nome);
	}

	public Squadra sceltaFormazione(Modulo modulo) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = serviceUser.findByUsername(auth.getName());

		Squadra squadra = user.getSquadra();
		squadra.setModulo(modulo);
		List<Giocatore> listaGiocatoriSquadra  = squadra.getListaGiocatori();
		List<Giocatore> listaRosa = new ArrayList<>();
		for (Giocatore giocatore : listaGiocatoriSquadra) {
				
				//prendo il primo portiere che incontro nella lista
				//trovare il modo per scegliere in modo random tra tutti i portieri presenti
				
				if(modulo.equals(Modulo.FORMAZIONE_UNO)) {
					//4-3-3
					for(Integer i = 1 ; listaRosa.size() <= 10; i++) {
						if(giocatore.getRuolo().equals(Ruolo.ROLE_PORTIERE) || giocatore.getRuolo().equals(Ruolo.ROLE_DIFENSORE) || giocatore.getRuolo().equals(Ruolo.ROLE_CENTROCAMPISTA) || giocatore.getRuolo().equals(Ruolo.ROLE_ATTACCANTE)) {
							if(giocatoreService.controlloRuolo(Ruolo.ROLE_PORTIERE) < 1 || giocatoreService.controlloRuolo(Ruolo.ROLE_DIFENSORE)<=4  || giocatoreService.controlloRuolo(Ruolo.ROLE_CENTROCAMPISTA)<=3 || giocatoreService.controlloRuolo(Ruolo.ROLE_ATTACCANTE)<=3) {
								listaRosa.add(giocatore);
							}
						}
					}
				} else if (modulo.equals(Modulo.FORMAZIONE_DUE)){
					//4-2-3-1
					
				} else {
					// 3-5-2
					
				}
		}
		return squadra;
	}


}
