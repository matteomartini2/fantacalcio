package it.dstech.fantacalcio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import it.dstech.fantacalcio.model.Squadra;
import it.dstech.fantacalcio.model.User;
import it.dstech.fantacalcio.repository.ISquadraRepository;

@Service
public class SquadraService {
	
	@Autowired
	private ISquadraRepository dao;
	
	@Autowired
	private UserService serviceUser;
	
	
	public Iterable<Squadra> findAll(){
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

	public Squadra create (Squadra squadra) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = serviceUser.findByUsername(auth.getName());
		 return dao.save(squadra);
	}
	
	public Squadra update (Squadra s) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = serviceUser.findByUsername(auth.getName());
		Squadra squadra = dao.findById(s.getId()).get();
		
		squadra.setListaGiocatori(s.getListaGiocatori());
		squadra.setModulo(s.getModulo());
		squadra.setNome(s.getNome());
		squadra.setPunteggio(s.getPunteggio());
		squadra.setUser(s.getUser());
		
		return dao.save(squadra);
		
	}
}
