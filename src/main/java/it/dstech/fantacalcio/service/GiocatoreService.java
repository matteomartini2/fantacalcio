package it.dstech.fantacalcio.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import it.dstech.fantacalcio.model.Giocatore;
import it.dstech.fantacalcio.model.User;
import it.dstech.fantacalcio.repository.IGiocatoreRepository;

@Service
public class GiocatoreService {
	
	@Autowired
	private UserService service;

	@Autowired
	private IGiocatoreRepository dao;
	
	public Giocatore createOne(Giocatore giocatore) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = service.findByUsername(auth.getName());
		return dao.save(giocatore);
	}
	
	public Iterable<Giocatore> createLista(ArrayList<Giocatore> listaGiocatori){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = service.findByUsername(auth.getName());
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
	
	public Optional<Giocatore> findOne(Long id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = service.findByUsername(auth.getName());
		return dao.findById(id);
	}
	
	public Iterable<Giocatore> findAll(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = service.findByUsername(auth.getName());
		return dao.findAll();
	}
	
	public Giocatore update(Giocatore giocatoreInput, Long id) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = service.findByUsername(auth.getName());
		Giocatore giocatoreDB = dao.findById(id).orElseThrow(() -> new Exception ());
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
