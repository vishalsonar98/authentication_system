package com.authentication_system.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BlacklistToken {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
	String token;

	public BlacklistToken(int id, String token) {
		super();
		this.id = id;
		this.token = token;
	}

	public BlacklistToken() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "BlacklistToken [id=" + id + ", token=" + token + "]";
	}
	
	
}
