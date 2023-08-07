package com.authentication_system.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class LoginVo {
	@NotEmpty(message = "email must not be empty")
	@NotNull(message = "email must not be null")
	@Email(message = "Enter well formed email")
	private String email;
	@NotEmpty(message = "password must not be empty")
	@NotNull(message = "password must not be empty")
	private String password;
	
	public LoginVo(@NotEmpty(message = "Enter valid email") @NotNull(message = "Enter valid email") String email,
			@NotEmpty(message = "Enter valid email") @NotNull(message = "Enter valid email") String password) {
		super();
		this.email = email;
		this.password = password;
	}
	public LoginVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "LoginVo [email=" + email + ", password=" + password + "]";
	}
	
	
}
