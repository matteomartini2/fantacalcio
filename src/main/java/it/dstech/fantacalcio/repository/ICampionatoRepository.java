package it.dstech.fantacalcio.repository;

import org.springframework.data.repository.CrudRepository;

import it.dstech.fantacalcio.model.Campionato;

public interface ICampionatoRepository extends CrudRepository<Campionato, Long>{
	

	Campionato findByNomeCampionato (String squadra);

	
}
