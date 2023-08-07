package com.authentication_system.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserVo {

	@NotEmpty(message = "Enter valid username")
	@NotNull(message = "Enter valid username")
	private String username;
	@NotEmpty(message = "Enter valid password")
	@NotNull(message = "Enter valid password")
	private String password;
	@NotEmpty(message = "Enter valid email")
	@NotNull(message = "Enter valid email")
	@Email(message = "Enter well formed email")
	private String email;

	public UserVo() {
	}

	public UserVo(String username, String password, String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserVo [username=" + username + ", password=" + password + ", email=" + email + "]";
	}

}
