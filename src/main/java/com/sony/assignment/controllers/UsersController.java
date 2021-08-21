package com.sony.assignment.controllers;

import java.util.Map;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sony.assignment.boundaries.CreateUserBoundary;
import com.sony.assignment.boundaries.UpdateUserBoundary;
import com.sony.assignment.boundaries.UserBoundary;
import com.sony.assignment.config.exceptions.QueryMissingKey;
import com.sony.assignment.services.UserService;

@RestController
@RequestMapping("users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsersController {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private ConversionService conversionService;
	
	
	@CrossOrigin("*")
	@GetMapping("displayAll")
	public UserBoundary[] get() {
		return Stream.of(service.getAll())
				.map(e -> this.conversionService.convert(e, UserBoundary.class))
				.toArray(UserBoundary[]::new);
	}
	
	@PostMapping()
	public UserBoundary post(@RequestBody CreateUserBoundary request) {
		return this.conversionService
				.convert(service.addUser(request),
						UserBoundary.class);
	}
	
	@DeleteMapping()
	public void delete(@RequestParam long id) {
		service.deleteUser(id);
	}
	
	@GetMapping()
	public UserBoundary get(@RequestParam Map<String, String> getUserQuery) {
		if(getUserQuery.containsKey("id"))
			return this.conversionService
				.convert(service.getUser(Long.parseLong(getUserQuery.get("id"))),
						UserBoundary.class);
		if(getUserQuery.containsKey("email"))
			return this.conversionService
					.convert(service.getUser(getUserQuery.get("email")),
							UserBoundary.class);
		throw new QueryMissingKey("getUserQuery", 
				new String[]{"id","email"});
	}
	
	
	@PutMapping()
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public UserBoundary put(@RequestParam long id,@RequestBody UpdateUserBoundary request) {
		return 
				this.conversionService
				.convert(service.updateUser(id,request),UserBoundary.class);
	}
	
	
	
}
