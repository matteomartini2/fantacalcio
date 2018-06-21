package it.dstech.fantacalcio.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import it.dstech.fantacalcio.model.Campionato;
import it.dstech.fantacalcio.model.Giocatore;
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
		giocatoreDB.setSquadra(giocatoreInput.getSquadra());
		giocatoreDB.setCampionato(giocatoreInput.getCampionato());
		giocatoreDB.setTitolare(giocatoreInput.isTitolare());
		return dao.save(giocatoreDB);
	}
	
	
}
