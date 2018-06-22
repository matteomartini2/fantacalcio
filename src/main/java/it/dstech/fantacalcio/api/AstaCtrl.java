package it.dstech.fantacalcio.api;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.dstech.fantacalcio.model.Asta;
import it.dstech.fantacalcio.model.User;
import it.dstech.fantacalcio.service.AstaService;
import it.dstech.fantacalcio.service.UserService;
import it.dstech.fantacalcio.service.auth.AuthService;

@RestController
@RequestMapping(value="/asta")
public class AstaCtrl {
	
	@Autowired
	AstaService serviceAsta;
	
	@Autowired
	UserService userService;
	@Autowired
	AuthService authService;
	
	
	@PostMapping("/{id}")
	public Asta save(@PathVariable("id") Long id) {
	return serviceAsta.save(id);	
	
	}
	
	@PostMapping("/")
	public Asta create(@RequestParam Long idGiocatore,
						@RequestBody ArrayList<LocalDateTime> date) {
		return serviceAsta.create(idGiocatore, date.get(0), date.get(0));
	}
	
	@GetMapping("/{id}")
	public Asta findOne(@PathVariable Long idAsta) {
		return serviceAsta.findOne(idAsta);
	}
	
	@PostMapping("/offerta")
	public void aggiungiOfferta(@RequestParam("idAsta") Long idAsta,
								@RequestParam("importo") Double importo) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByUsername(auth.getName());
		
		try {
			serviceAsta.aggiungiOfferta(idAsta, user.getId(), importo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
