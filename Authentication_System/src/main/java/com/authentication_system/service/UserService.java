package com.authentication_system.service;

import com.authentication_system.entity.User;
import com.authentication_system.vo.UserVo;

/**
 * Provides functionality to add user to the database
 */
public interface UserService {
	/**
	 * helps to save users data to database with the help of repository
	 * @param user user dto
	 * @return User entity object
	 */
	User addUser(UserVo user);
}
