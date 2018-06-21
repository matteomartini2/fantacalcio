package it.dstech.fantacalcio.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.dstech.fantacalcio.model.User;
import it.dstech.fantacalcio.service.UserService;

@Controller
@RequestMapping("/user")
public class UserCtrl {

	 @Autowired
	 private UserService userService;
	 
	 @RequestMapping(method = RequestMethod.POST, value = "create")
	 public User create (@RequestBody User user) {
		 return userService.create(user);
	 }
	 
	 @RequestMapping(method = RequestMethod.POST, value = "createList")
	 public Iterable<User> createList (@RequestBody Iterable<User> listaUser){
		 return userService.createList(listaUser);
	 }
	 
	 @RequestMapping(method = RequestMethod.GET, value = "findAll")
	 public Iterable<User> findAll() {
		 return userService.findAll();
	 }
	 
	 @RequestMapping(method = RequestMethod.GET, value = "findOne")
	 public User findOne(@RequestParam ("id") Long id) throws Exception {
		 return userService.findOne(id);
	 }
	 
	 @RequestMapping(method = RequestMethod.DELETE, value = "deleteAll")
	 public void deleteAll() {
		 userService.deleteAll();
	 }
	 
	 @RequestMapping(method = RequestMethod.DELETE, value = "deleteOne")
	 public void deleteOne(@RequestParam ("id") Long id) {
		 userService.deleteOne(id);
	 }
	 
	 @RequestMapping(method = RequestMethod.POST, value = "update")
	 public User update (@RequestBody User userInput) throws Exception {
		 User userDb = findOne(userInput.getId());
		 userDb.setUsername(userInput.getUsername());
		 userDb.setPassword(userInput.getPassword());
		 return userService.create(userDb);
	 }

	 @RequestMapping(method = RequestMethod.GET, value = "findByUsername")
	 public User findByUsername (@RequestParam ("username") String username) {
		 return userService.findByUsername(username);
	 }
}
