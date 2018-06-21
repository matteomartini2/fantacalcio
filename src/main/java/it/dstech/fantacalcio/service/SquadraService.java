package it.dstech.fantacalcio.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import it.dstech.fantacalcio.model.Campionato;
import it.dstech.fantacalcio.model.Squadra;
import it.dstech.fantacalcio.model.User;
import it.dstech.fantacalcio.repository.ISquadraRepository;

@Service
public class SquadraService {

	@Autowired
	private ISquadraRepository dao;

	@Autowired
	private UserService serviceUser;

	@Autowired
	private CampionatoService campionatoService;

	public Iterable<Squadra> findAll() {
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

	public Squadra create(Squadra squadra, Long idCampionato) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = serviceUser.findByUsername(auth.getName());
		Campionato campionato = campionatoService.findOne(idCampionato);
		LocalDate dataOggi = LocalDate.now();
		if(dataOggi.isAfter(campionato.getDataInizio())) {
			throw new Exception ("Campionato non disponibile.");
		}
		campionato.getListaSquadre().add(squadra);
		return dao.save(squadra);
	}

	public Squadra update(Squadra s, Long idCampionato) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = serviceUser.findByUsername(auth.getName());
		Campionato campionato = campionatoService.findOne(idCampionato);
		LocalDate dataOggi = LocalDate.now();
		if(dataOggi.isAfter(campionato.getDataInizio())) {
			throw new Exception ("Campionato non disponibile.");
		}
		Squadra squadra = dao.findById(s.getId()).get();
		squadra.setListaGiocatori(s.getListaGiocatori());
		squadra.setModulo(s.getModulo());
		squadra.setNome(s.getNome());
		squadra.setPunteggio(s.getPunteggio());
		squadra.setUser(s.getUser());
		squadra.setDataRegistrazione(s.getDataRegistrazione());
		campionato.getListaSquadre().add(s);
		return dao.save(squadra);

	}

	public Squadra associaSquadraCampionato(Squadra squadra, String NomeCampionato) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = serviceUser.findByUsername(auth.getName());

		Campionato campionato = campionatoService.findByUsername(NomeCampionato);

		List<Squadra> listaSquadre = campionato.getListaSquadre();
		if (listaSquadre == null)
			listaSquadre = new ArrayList<>();
		listaSquadre.add(squadra);
		campionato.setListaSquadre(listaSquadre);
		return dao.save(squadra);

	}

	public Squadra findByNome(String nome) {

		return dao.findBynome(nome);
	}
	
	
}
