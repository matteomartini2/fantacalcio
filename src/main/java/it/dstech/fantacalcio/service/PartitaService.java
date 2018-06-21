package it.dstech.fantacalcio.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.dstech.fantacalcio.model.Campionato;
import it.dstech.fantacalcio.model.Partita;
import it.dstech.fantacalcio.model.Squadra;
import it.dstech.fantacalcio.repository.IPartitaRepository;

@Service
public class PartitaService {

	@Autowired
	private IPartitaRepository dao;

	@Autowired
	private CampionatoService service;

	@Autowired
	private SquadraService serviceSquadra;

	//dataInizio e dataFine 
	//devo fare 38 partite 
	//se le squadre sono dispari, una squadra riposa.
	//sono due le squadre e in una settimana una squadra non puo giocare due volte
	//squadraUno (random) e squadraDue (random, meno squadraUno)
	//n partite, in base a quante squadre abbiamo nel DB

	public void partiteRandom(Long idCampionato) throws Exception {
		Campionato campionato = service.findOne(idCampionato);
		List<Squadra> listaSquadre = campionato.getListaSquadre();
		campionato.getListaGiocatoriDisponibili();
		Partita partita = new Partita();
		partita.setData(campionato.getDataInizio());
		dao.save(partita);
	}
}
