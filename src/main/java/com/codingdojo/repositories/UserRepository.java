package com.codingdojo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.codingdojo.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
	@SuppressWarnings("unchecked")
	User save( User newUser );
	
	List<User> findByUserName( String userName );
	
	List<User> findByEmail( String email );
}
