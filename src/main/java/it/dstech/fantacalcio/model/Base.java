package it.dstech.fantacalcio.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

@MappedSuperclass
public abstract class Base {
	

	@Id
	@GeneratedValue
	@SequenceGenerator(name = "nome", initialValue = 1, allocationSize = 1)
	private Long id;
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

}
