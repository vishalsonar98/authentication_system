package com.authentication_system.controller;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.authentication_system.entity.User;
import com.authentication_system.service.BlacklistTokenService;
import com.authentication_system.service.UserService;
import com.authentication_system.service.impl.CustomUserDetailsService;
import com.authentication_system.util.JwtUtil;
import com.authentication_system.vo.LoginVo;
import com.authentication_system.vo.ResponseVo;
import com.authentication_system.vo.UserVo;

/**
 * Controller class to handle Rest API requests and response
 */
@RestController
public class MainController {

	@Autowired
	private UserService userService;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private BlacklistTokenService blacklistTokenService;

	/**
	 * Method maps /register endpoint and helps to call related service to save the
	 * data of user
	 * 
	 * @param user          DTO of user which accepts values for to save
	 * @param bindingResult binds errors if there any
	 * @return ResponseVo object which returns proper response to the client
	 */
	@PostMapping("/register")
	public ResponseVo registerUser(@RequestBody @Valid UserVo user, BindingResult bindingResult) {
		ResponseVo response;
		User responseUser;

		if (bindingResult.hasErrors()) {
			return new ResponseVo(500, "Validation Error", bindingResult.getAllErrors().stream()
					.map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
		}
		responseUser = userService.addUser(user);
		response = new ResponseVo(200, "Ok", responseUser);

		return response;
	}

	/**
	 * Method maps /login endpoint and helps user to generate JWT token
	 * 
	 * @param userCredentials LoginVo to bind data in object
	 * @param bindingResult   binds errors if there any
	 * @return ResponseVo object which returns proper response to the client
	 */
	@PostMapping("/login")
	public ResponseVo generateToken(@RequestBody @Valid LoginVo userCredentials, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseVo(500, "Validation Error", bindingResult.getAllErrors().stream()
					.map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
		}

		try {
			UserDetails userDetails = customUserDetailsService.loadUserByUsername(userCredentials.getEmail());

			if (!passwordEncoder.matches(userCredentials.getPassword(), userDetails.getPassword())) {
				return new ResponseVo(401, "Unauthorized", "Enter valid password");
			}
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(userCredentials.getEmail(), userCredentials.getPassword()));
			String token = jwtUtil.generateToken(userDetails);
			return new ResponseVo(200, "Ok", token);
		} catch (Exception e) {
			String message = e.getMessage();
			return new ResponseVo(401, "Unauthorized", message);
		}
	}

	/**
	 * Method maps /admin endpoint.If the user is admin then user can access this
	 * endpoint.
	 * 
	 * @return ResponseVo object which returns proper response to the client
	 */
	@GetMapping("/admin")
	public ResponseVo adminPanel() {
		return new ResponseVo(200, "ok", "Admin Panel");
	}

	/**
	 * Method maps /signout endpoint. This method invalidates JWT token.
	 * 
	 * @param authHeader Authorization header which comes along with the request
	 * @return ResponseVo object which returns proper response to the client
	 */
	@PostMapping("/signout")
	public ResponseVo logout(@RequestHeader("Authorization") String authHeader) {
		String jwtToken = authHeader.substring(7);
		blacklistTokenService.addTokenToBlacklist(jwtToken);
		return new ResponseVo(200, "Ok", "User is loggedout");
	}
}
