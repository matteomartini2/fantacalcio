package it.dstech.fantacalcio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.dstech.fantacalcio.model.User;
import it.dstech.fantacalcio.repository.IUserRepository;

@Service
public class UserService {
	
	@Autowired
	private IUserRepository userRepository;
	
	public User findByUsername (String username) {
		return userRepository.findByUsername(username);
	}

}
