package it.dstech.fantacalcio.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.dstech.fantacalcio.service.GiocatoreService;

@RestController
@RequestMapping("/giocatore")
public class GiocatoreCtrl {

	@Autowired
	private GiocatoreService service;
	
	
}
