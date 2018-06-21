package it.dstech.fantacalcio.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.dstech.fantacalcio.model.Partita;

public interface IPartitaRepository extends CrudRepository<Partita, Long>{
	
	List<Partita> findByData (LocalDate data);
}
