package com.sony.assignment.config.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserAlreadyExists extends RuntimeException {

	private static final long serialVersionUID = 6121256266161264118L;
	
	public UserAlreadyExists(String email) {
		super(String.format("User email: %s already exists in the system",email));
	}
}

