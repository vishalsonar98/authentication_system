package com.authentication_system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;





@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;
	
	@Column(name = "username")
	private String userName;

	private String password;

	@Column(unique = true)
	private String email;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="role_id")
	private Role role;

	public User(int id, String userName, String password, String email, Role role) {
		super();
		Id = id;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.role = role;
	}

	public User() {
				
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [Id=" + Id + ", userName=" + userName + ", password=" + password + ", email=" + email + ", role="
				+ role.getName() + "]";
	}
	
	
}
