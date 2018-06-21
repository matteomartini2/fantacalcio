package it.dstech.fantacalcio.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.dstech.fantacalcio.model.Partita;
import it.dstech.fantacalcio.repository.IPartitaRepository;

@Service
public class PartitaService {

	@Autowired
	private IPartitaRepository dao;
	//dataInizio e dataFine 
	//devo fare 38 partite 
	//sono due le squadre e in una settimana una squadra non puo giocare due volte
	//squadraUno (random) e squadraDue (random, meno squadraUno)
	//n partite, in base a quante squadre abbiamo nel DB
	
	
	
	
}
