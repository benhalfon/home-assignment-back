package com.sony.assignment.config.security.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sony.assignment.services.UserService;

@Service
public class UserSecurityServiceImplementation implements UserSecurityLogic {

	@Autowired
	private UserService service;
	
	@Override
	public UserPrincipal GetUserPrincipalbyBasicAuth(String basicAuthUsername) {
		return GetUserPrincipal(basicAuthUsername);
	}
	
	private UserPrincipal GetUserPrincipal(String username) {
		return new UserPrincipal(service.getUser(username));
	}

}
