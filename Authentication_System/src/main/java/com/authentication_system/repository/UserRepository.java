package com.authentication_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.authentication_system.entity.User;

/**
 * Manages data of User entity class and provides various methods to operate
 * over data
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	/**
	 * finds User using email String
	 * @param email 
	 * @return User entity
	 */
	User findByEmail(String email);

}
