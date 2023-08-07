package com.authentication_system.service;

/**
 * Helps to add JWT token in blacklist table or check whether token is present inside blacklist database or not.
 */
public interface BlacklistTokenService {

	/**
	 * This method add token to blacklist_token table.
	 * @param token JWT token String
	 */
	void addTokenToBlacklist(String token);
	/**
	 * This method verify token which is black listed or not  
	 * @param token JWT token String
	 * @return true if token is in blacklist table otherwise returns false
	 */
	boolean isTokenBlacklisted(String token);
}
