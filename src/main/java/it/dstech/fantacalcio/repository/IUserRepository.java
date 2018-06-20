package it.dstech.fantacalcio.repository;

import org.springframework.data.repository.CrudRepository;

import it.dstech.fantacalcio.model.User;

public interface IUserRepository extends CrudRepository<User, Long>{

}
