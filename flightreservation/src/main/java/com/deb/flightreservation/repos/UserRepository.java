package com.deb.flightreservation.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.deb.flightreservation.entities.User;

public interface UserRepository extends CrudRepository<User,Long> 
{
	
	User findByEmail(String email);
		
}
