package com.sony.assignment.config.security.auth;

public interface UserSecurityLogic {
	public UserPrincipal GetUserPrincipalbyBasicAuth(String basicAuthUsername);
}
