package it.dstech.fantacalcio.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.dstech.fantacalcio.model.Asta;
import it.dstech.fantacalcio.model.User;
import it.dstech.fantacalcio.repository.IAstaRepository;
import it.dstech.fantacalcio.repository.IUserRepository;
import it.dstech.fantacalcio.service.auth.AuthService;

@Service
public class AstaService {
	
	@Autowired
	IAstaRepository dao;
	
	@Autowired
	IUserRepository daoUser;
	
	@Autowired
	GiocatoreService serviceGiocatore;
	
	@Autowired
	UserService serviceUtente;
	
	@Autowired
	UserService userService;
	
	@Autowired
	AuthService authService;
	
	
	public Asta save(Long id) {
		return dao.save(dao.findById(id).get());
	}
	
	public Asta create(Long idGiocatore, LocalDateTime dataInizio, LocalDateTime dataFine) {
		Asta asta= new Asta();
		asta.setDataInizio(dataInizio);
		asta.setDataFine(dataFine);
		asta.setIdGiocatore(idGiocatore);
		return dao.save(asta);		
	}
	
	public Asta findOne(Long id) {
		
		return dao.findById(id).get();
	}
	
	public Iterable<Asta> findAll(){
		return dao.findAll();
	}
	
	public void update(Asta asta) {
		Asta temp=dao.findById(asta.getId()).get();
		temp.setDataFine(asta.getDataFine());
		temp.setDataInizio(asta.getDataInizio());
		temp.setIdGiocatore(asta.getIdGiocatore());
		temp.setOfferte(asta.getOfferte());
		dao.save(temp);
	}
	
	public void delete(Long id) {
		dao.deleteById(id);
	}
	
	public void aggiungiOfferta(Long idAsta,Long idUtente,Double importo) throws Exception {
		Asta asta=findOne(idAsta);
		User utente=new User();
		LocalDateTime orario=LocalDateTime.now();
		try {
			utente= serviceUtente.findOne(idUtente);
		} catch (Exception e) {
			throw new Exception("Utente non trovato");
		}
		if(importo<1D)throw new Exception("Impossibile puntare");
		if(utente.getCreditoDaSpendere()<importo) throw new Exception("Credito insufficiente");
		if(orario.isAfter(asta.getDataFine())) throw new Exception("Asta finita");
		if(orario.isBefore(asta.getDataInizio())) throw new Exception("Asta non ancora iniziata");
		
		asta.getOfferte().put(idUtente, importo);
		dao.save(asta);
	}
	
	public void concludiAsta(Long idAsta) throws Exception {
		Asta asta= findOne(idAsta);
		if(asta.getOfferte()==null) throw new Exception("Nessuna offerta");
		Long idVincitore=null;
		Double max=0D;
		for(Long i:asta.getOfferte().keySet()) {
			Double temp=asta.getOfferte().get(i);
			if(temp>max) {
				max=temp;
				idVincitore=i;
			}
		}
		User utente=serviceUtente.findOne(idVincitore);
		utente.setCreditoDaSpendere(utente.getCreditoDaSpendere()-Integer.parseInt(max.toString()));
		utente.getSquadra().getListaGiocatori().add(serviceGiocatore.findOne(asta.getIdGiocatore()));
		asta.setDataFine(LocalDateTime.now());
		dao.save(asta);
		daoUser.save(utente);	
	}
	
	
	
}
