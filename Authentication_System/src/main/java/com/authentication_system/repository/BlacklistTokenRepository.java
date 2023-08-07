package com.authentication_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.authentication_system.entity.BlacklistToken;

/**
 * Manages data of BlacklistToken entity class and provides various methods to
 * operate over data
 */
public interface BlacklistTokenRepository extends JpaRepository<BlacklistToken, Integer> {

	/**
	 * finds black listed token inside database
	 * 
	 * @param token JWT token string
	 * @return BlacklistToken entity
	 */
	BlacklistToken findByToken(String token);

}
