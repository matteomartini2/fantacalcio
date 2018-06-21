package it.dstech.fantacalcio.api;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.dstech.fantacalcio.model.Giocatore;
import it.dstech.fantacalcio.model.User;
import it.dstech.fantacalcio.service.GiocatoreService;

@RestController
@RequestMapping("/giocatore")
public class GiocatoreCtrl {

	@Autowired
	private GiocatoreService service;
	
	@RequestMapping (method = RequestMethod.POST, value = "createOne")
	public Giocatore createOne(@RequestBody Giocatore giocatore) {
		return service.createOne(giocatore);
	}
	
	@RequestMapping (method = RequestMethod.POST, value = "createLista")
	public Iterable<Giocatore> createLista(@RequestBody ArrayList<Giocatore> listaGiocatori){
		return service.createLista(listaGiocatori);
	}
	
	@RequestMapping (method = RequestMethod.DELETE, value = "deleteOne")
	public void deleteOne(@RequestParam(name = "id") Long id) {
		service.deleteOne(id);
	}
	
	@RequestMapping (method = RequestMethod.DELETE, value = "deleteAll")
	public void deleteAll() {
		service.deleteAll();
	}
	
	@RequestMapping (method = RequestMethod.GET, value = "findOne")
	public Optional<Giocatore> findOne(@RequestParam(name = "id") Long id) {
		return service.findOne(id);
	}
	
	@RequestMapping (method = RequestMethod.GET, value = "findAll")
	public Iterable<Giocatore> findAll(){
		return service.findAll();
	}
	
	@RequestMapping (method = RequestMethod.PUT, value = "update")
	public Giocatore update(@RequestBody Giocatore giocatoreInput,@RequestParam(name = "id")  Long id) throws Exception {
		return service.update(giocatoreInput, id);
	}
}
