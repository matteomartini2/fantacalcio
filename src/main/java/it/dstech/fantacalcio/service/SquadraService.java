package it.dstech.fantacalcio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.dstech.fantacalcio.model.Squadra;
import it.dstech.fantacalcio.repository.ISquadraRepository;

@Service
public class SquadraService {
	
	@Autowired
	ISquadraRepository dao;
	
	
	public Iterable<Squadra> findAll(){
		 return dao.findAll();
		
	}
	
	public Squadra findOne(Long id) throws Exception {
		 Squadra squadra = dao.findById(id).orElseThrow(() -> new Exception());
		return squadra;
	}
	
	public void deleteOne(Long id) {
		  dao.deleteById(id);
	}
	
	public void deleteAll() {
		dao.deleteAll();
	}

	public Squadra create (Squadra squadra) {
		 return dao.save(squadra);
	}
	
	public Squadra update (Squadra s) {
		Squadra squadra = dao.findById(s.getId()).get();
		
		squadra.setListaGiocatori(s.getListaGiocatori());
		squadra.setModulo(s.getModulo());
		squadra.setNome(s.getNome());
		squadra.setPunteggio(s.getPunteggio());
		squadra.setUser(s.getUser());
		
		return dao.save(squadra);
		
	}
}
