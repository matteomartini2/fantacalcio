package it.dstech.fantacalcio.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.dstech.fantacalcio.model.Modulo;
import it.dstech.fantacalcio.model.Squadra;
import it.dstech.fantacalcio.service.SquadraService;

@RestController
@RequestMapping("/squadra")
public class SquadraCtrl {

	@Autowired
	SquadraService serviceSquadra;

	@GetMapping("/findAll")
	public Iterable<Squadra> findAll() {
		return serviceSquadra.findAll();

	}

	@GetMapping("/findOne/{id}")
	public Squadra findOne(@PathVariable ("id")Long id) throws Exception {
		return serviceSquadra.findOne(id);

	}
	
	@PostMapping("/update")
	public void update(@RequestBody Squadra s, @RequestParam (name = "id") Long idCampionato ) throws Exception {
		serviceSquadra.update(s, idCampionato);
	
	}
	

	@DeleteMapping("/deleteOne/{id}")
	public void delete(@PathVariable ("id") Long id) {
		serviceSquadra.deleteOne(id);
		
	}
	

	@DeleteMapping("/deleteAll")
	public void deleteAll() {
		serviceSquadra.deleteAll();
		
	}
	
	@PostMapping("/create")
	public Squadra create(@RequestBody Squadra squadra, @RequestParam (name = "id") Long idCampionato) throws Exception {
		
		return serviceSquadra.create(squadra, idCampionato);
	}
	
	@PostMapping("/sceltaFormazione")
	public Squadra sceltaFormazione(@RequestParam (" idModulo") Modulo modulo) {
		return serviceSquadra.sceltaFormazione(modulo);
	}
}
