package it.dstech.fantacalcio.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.dstech.fantacalcio.model.Giocatore;
import it.dstech.fantacalcio.repository.IGiocatoreRepository;

@Service
public class GiocatoreService {

	@Autowired
	private IGiocatoreRepository dao;
	
	public Giocatore createOne(Giocatore giocatore) {
		
		return dao.save(giocatore);
	}
	
	public Iterable<Giocatore> createLista(ArrayList<Giocatore> listaGiocatori){
		return dao.saveAll(listaGiocatori);
	}
	
	public void deleteOne(Long id) {
		dao.deleteById(id);
	}
}
