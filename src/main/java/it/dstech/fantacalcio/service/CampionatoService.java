package it.dstech.fantacalcio.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import it.dstech.fantacalcio.model.Campionato;
import it.dstech.fantacalcio.model.User;
import it.dstech.fantacalcio.repository.ICampionatoRepository;

@Service
public class CampionatoService {

	@Autowired
	private ICampionatoRepository campionatoRepository;
	
	@Autowired
	private UserService userService;
	
	public Campionato findOne(Long id) throws Exception {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByUsername(auth.getName());
		
		return campionatoRepository.findById(id).orElseThrow(()->new Exception());
	}
	
	public Iterable<Campionato> findAll(){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByUsername(auth.getName());
		
		return campionatoRepository.findAll();
	}
	
	public void deleteOne(Long id) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByUsername(auth.getName());
		
		
		campionatoRepository.deleteById(id);
	}
	
	public void deleteAll() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByUsername(auth.getName());
		
		
		campionatoRepository.findAll();
	}
	
	public Campionato create (Campionato campionato) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByUsername(auth.getName());
		
		return campionatoRepository.save(campionato);
	}
	
	public Campionato update (Campionato campionato, Long id) throws Exception {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByUsername(auth.getName());
		
		//Campionato campionatoNuovo = campionatoRepository.findById(id).orElseThrow(()->new Exception());
		Campionato campionatoNuovo = findOne(id);
		campionatoNuovo.setNomeCampionato(campionato.getNomeCampionato());
		campionatoNuovo.setNazioneCampionato(campionato.getNazioneCampionato());
		campionatoNuovo.setListaSquadre(campionato.getListaSquadre());
		campionatoNuovo.setListaGiocatoriDisponibili(campionato.getListaGiocatoriDisponibili());
		return campionatoNuovo;
	}
	

	/*CREA CAMPIONATO LO PUO FARE SOLO L'ADMIN
	 * */


	public Campionato findByUsername(String username) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByUsername(auth.getName());
		
		return campionatoRepository.findByNomeCampionato(username);
	}
	
	

}
