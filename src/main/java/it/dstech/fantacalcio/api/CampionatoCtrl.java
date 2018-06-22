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

import it.dstech.fantacalcio.model.Campionato;
import it.dstech.fantacalcio.service.CampionatoService;

@RestController
@RequestMapping(value="/campionato")
public class CampionatoCtrl {

	@Autowired
	CampionatoService serviceCampionato;
	
	@GetMapping("/findOne/{id}")
	public Campionato findOne(@PathVariable Long id) throws Exception {
		return serviceCampionato.findOne(id);
		
	}
	@GetMapping("/findAll")
	public Iterable<Campionato> findAll(){
		return serviceCampionato.findAll();
	}
	@PostMapping("/update")
	public void update(@RequestBody Campionato campionato, 
			@RequestParam ("id")Long id) throws Exception {
		serviceCampionato.update(campionato,id);
	}
	@DeleteMapping("/deleteOne/{id}")
	public void delete(@PathVariable Long id) {
		serviceCampionato.deleteOne(id);
	}
	@DeleteMapping("/deleteAll")
	public void deleAll() {
		serviceCampionato.deleteAll();
	}
	@GetMapping("/create")
	public Campionato create(@RequestBody Campionato campionato) {
		return serviceCampionato.create(campionato);
		
	}
}
