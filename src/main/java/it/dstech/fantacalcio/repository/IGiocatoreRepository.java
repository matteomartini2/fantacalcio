package it.dstech.fantacalcio.repository;

import org.springframework.data.repository.CrudRepository;

import it.dstech.fantacalcio.model.Giocatore;

public interface IGiocatoreRepository extends CrudRepository<Giocatore, Long>{
	
}
