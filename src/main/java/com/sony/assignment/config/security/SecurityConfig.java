package com.sony.assignment.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sony.assignment.config.security.auth.UserPrincipalDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true,jsr250Enabled = true)  
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final UserPrincipalDetailsService applicationUserService;
	
	private final PasswordConfig passwordConfig;
	
	@Autowired
	public SecurityConfig(UserPrincipalDetailsService applicationUserService,
			PasswordConfig passwordConfig){
		this.applicationUserService = applicationUserService;
		this.passwordConfig = passwordConfig;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {return passwordConfig.encoder();}
	
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider(){
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService(applicationUserService);
		return provider;
	}
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web.ignoring().antMatchers(HttpMethod.POST,"/users");
	    web.ignoring().antMatchers(HttpMethod.GET,"/health");
	    web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	    web.ignoring().antMatchers("/error");
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
        .authorizeRequests()
        .anyRequest().authenticated().and().httpBasic();
	}
}