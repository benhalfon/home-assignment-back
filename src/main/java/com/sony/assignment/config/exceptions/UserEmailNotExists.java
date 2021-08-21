package com.sony.assignment.config.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserEmailNotExists extends RuntimeException {

	private static final long serialVersionUID = 6371254213181214118L;
	
	public UserEmailNotExists(String email) {
		super(String.format("User email: %s not found",email));
	}
}
