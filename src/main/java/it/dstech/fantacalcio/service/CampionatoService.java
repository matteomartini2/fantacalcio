package it.dstech.fantacalcio.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.dstech.fantacalcio.model.Campionato;
import it.dstech.fantacalcio.repository.ICampionatoRepository;

@Service
public class CampionatoService {

	@Autowired
	private ICampionatoRepository campionatoRepository;
	
	public Campionato findOne(Long id) throws Exception {
		return campionatoRepository.findById(id).orElseThrow(()->new Exception());
	}
	public Iterable<Campionato> findAll(){
		return campionatoRepository.findAll();
	}
	public void deleteOne(Long id) {
		campionatoRepository.deleteById(id);
	}
	public void deleteAll() {
		campionatoRepository.findAll();
	}
	public Campionato create (Campionato campionato) {
		return campionatoRepository.save(campionato);
	}
	public Campionato update (Campionato campionato, Long id) throws Exception {
		Campionato campionatoNuovo = campionatoRepository.findById(id).orElseThrow(()->new Exception());
		campionatoNuovo.setNomeCampionato(campionato.getNomeCampionato());
		campionatoNuovo.setNazioneCampionato(campionato.getNazioneCampionato());
		campionatoNuovo.setListaSquadre(campionato.getListaSquadre());
		campionatoNuovo.setListaGiocatoriDisponibili(campionato.getListaGiocatoriDisponibili());
		return campionatoNuovo;
	}
	
	
	
}
