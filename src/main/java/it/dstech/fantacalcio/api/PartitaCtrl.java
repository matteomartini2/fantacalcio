package it.dstech.fantacalcio.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.dstech.fantacalcio.service.PartitaService;

@RestController
@RequestMapping("/partita")
public class PartitaCtrl {

	@Autowired
	private PartitaService service;

	
	@RequestMapping(method = RequestMethod.POST, value = "primaPartita")
	public void primaPartita(@RequestParam ("idS1")Long squadraUno,@RequestParam ("idS2") Long squadraDue, @RequestParam("idC")Long idCampionato) throws Exception {
		 service.primaPartita(squadraUno, squadraDue, idCampionato);
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "risultatoPartita")
	public Long risultatoPartita (@RequestParam("idP")Long idPartita) throws Exception {
		return service.risultatoPartita(idPartita);
		
	}
}
