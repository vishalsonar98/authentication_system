package com.authentication_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.authentication_system.entity.Role;
import com.authentication_system.entity.User;
import com.authentication_system.repository.RoleRepository;
import com.authentication_system.repository.UserRepository;
import com.authentication_system.service.UserService;
import com.authentication_system.util.MapObject;
import com.authentication_system.vo.UserVo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private MapObject mapObject;
	
	@Override
	public User addUser(UserVo user) {
		User response = null;
		User userEntity = mapObject.mapUserVoToUserEntity(user);
		Role role = null;

		role = roleRepository.findById(101).get();
		userEntity.setRole(role);

		response = userRepository.save(userEntity);

		return response;
	}

}
