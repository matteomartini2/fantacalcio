package it.dstech.fantacalcio.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "user")
public class User  extends Base{
	
	@Column (name = "username", nullable = false, unique = true)
	private String username;
	

}
