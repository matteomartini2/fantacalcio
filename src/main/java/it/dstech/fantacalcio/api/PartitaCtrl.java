package it.dstech.fantacalcio.api;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.dstech.fantacalcio.model.Squadra;
import it.dstech.fantacalcio.service.PartitaService;

@RestController
@RequestMapping("/partita")
public class PartitaCtrl {

	@Autowired
	private PartitaService service;

	/*
	@RequestMapping(method = RequestMethod.POST, value = "primaPartita")
	public void primaPartita(@RequestParam ("idS1")Long squadraUno,@RequestParam ("idS2") Long squadraDue, @RequestParam("idC")Long idCampionato) throws Exception {
		 service.primaPartita(squadraUno, squadraDue, idCampionato);
		
	}
	*/
	
	@RequestMapping(method = RequestMethod.POST, value = "/scontroSettimanale")
	public void scontroSettimanale(@RequestParam("idC") Long idCampionato, @RequestParam ("idS1") Long idSquadraHome, @RequestParam ("idS2") Long idSquadraGuest, @RequestParam ("dataPartita") @DateTimeFormat(iso = ISO.DATE) LocalDate dataPartita) throws Exception {
		service.scontroSettimanale(idCampionato, idSquadraHome, idSquadraGuest, dataPartita);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/risultatoPartita")
	public Squadra risultatoPartita (@RequestParam("idP")Long idPartita) throws Exception {
		return service.risultatoPartita(idPartita);
		
	}
}
