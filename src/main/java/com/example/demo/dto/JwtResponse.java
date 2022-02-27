package com.example.demo.dto;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private final String userName;
	private final String email;

	public JwtResponse(String jwttoken, String userName, String email) {
		this.jwttoken = jwttoken;
		this.userName = userName;
		this.email = email;
	}

	public String getUserName() {
		return this.userName;
	}

	public String getToken() {
		return this.jwttoken;
	}

	public String getEmail() {
		return email;
	}
}
