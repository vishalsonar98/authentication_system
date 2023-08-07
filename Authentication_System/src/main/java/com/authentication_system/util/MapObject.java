package com.authentication_system.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.authentication_system.entity.User;
import com.authentication_system.vo.UserVo;

/**
 * Provides util methods to map one object to another
 */
@Component
public class MapObject {
	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * Maps UserVo object to the User entity object
	 * 
	 * @param user UserVo object
	 * @return User entity object
	 */
	public User mapUserVoToUserEntity(UserVo user) {
		User result = new User();
		result.setUserName(user.getUsername());
		result.setEmail(user.getEmail());
		result.setPassword(passwordEncoder.encode(user.getPassword()));
		return result;
	}
}
