package com.authentication_system.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.authentication_system.entity.User;
import com.authentication_system.repository.UserRepository;
/**
 * Implementation for UserDetailsService interface
 */
@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if (user==null) {
			throw new UsernameNotFoundException("Enter valid email");
		}
		ArrayList<SimpleGrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority(user.getRole().getName()));
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), roles);
	}

}
