package it.dstech.fantacalcio.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.dstech.fantacalcio.model.User;
import it.dstech.fantacalcio.service.UserService;
import it.dstech.fantacalcio.service.auth.AuthService;

@RestController
@RequestMapping(value="/user")
public class UserCtrl {

	@Autowired
	private UserService userService;
	@Autowired
	private AuthService authService;
	@Autowired
	private PasswordEncoder encoder;


	@RequestMapping (method = RequestMethod.DELETE, value = "/deleteOne")
	public void deleteOne (@RequestParam ("id") Long id) {
		userService.deleteOne(id);
	}
	
	@RequestMapping (method = RequestMethod.DELETE, value = "/deleteAll")
	public void deleteAll() {
		userService.deleteAll();
	}
	
	@RequestMapping (method = RequestMethod.GET, value = "/findOne")
	public User findOne (@RequestParam ("id") Long id) throws Exception {
		return userService.findOne(id);
	}
	
	@RequestMapping (method = RequestMethod.GET, value = "/findAll")
	public Iterable<User> findAll() {
		return userService.findAll();
	}
	
	@RequestMapping (method = RequestMethod.PUT, value = "/update")
	public User update (@RequestBody User userInput) throws Exception {
		return userService.update(userInput);
	}
	
	@PostMapping("/login")
	public UserDetails authenticate (@RequestBody User user) throws Exception{
		return authService.authenticate(user);	
	}
	
	@PostMapping("/register")
	public User addUser (@RequestBody User user) throws Exception{
		user.setPassword(encoder.encode(user.getPassword()));
		return userService.create(user);	
	}
	
	@GetMapping ("/getusermodel")
	public User getModel(){
		return new User();

	}
	
	@RequestMapping ("/findByUsername/{username}")
	public User findByUsername (@PathVariable ("username") String username) {
		return userService.findByUsername(username);
	}
	
}
