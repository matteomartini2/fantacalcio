package it.dstech.fantacalcio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.dstech.fantacalcio.model.User;
import it.dstech.fantacalcio.repository.IUserRepository;

@Service
public class UserService {
	
	@Autowired
	private IUserRepository userRepository;
	
	public User create (User user) {
		return userRepository.save(user);
	}
	public Iterable<User> createList (Iterable<User> listaUser){
		return userRepository.saveAll(listaUser);
	}
	public Iterable<User> findAll() {
		return userRepository.findAll();
	}
	public User findOne(Long id) throws Exception {
		return userRepository.findById(id).orElseThrow(()-> new Exception());
	}
	public void deleteAll() {
		userRepository.deleteAll();
	}
	public void deleteOne(Long id) {
		userRepository.deleteById(id);
	}
	public User update (User userInput) throws Exception {
		User userDb = findOne(userInput.getId());
		userDb.setUsername(userInput.getUsername());
		userDb.setPassword(userInput.getPassword());
		return userRepository.save(userDb);
	}
	
	public User findByUsername (String username) {
		return userRepository.findByUsername(username);
	}

}
