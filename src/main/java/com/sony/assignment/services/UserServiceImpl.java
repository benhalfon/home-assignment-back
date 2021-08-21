package com.sony.assignment.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import com.sony.assignment.boundaries.CreateUserBoundary;
import com.sony.assignment.boundaries.UpdateUserBoundary;
import com.sony.assignment.config.exceptions.UserAlreadyExists;
import com.sony.assignment.config.exceptions.UserEmailNotExists;
import com.sony.assignment.config.exceptions.UserIdNotExists;
import com.sony.assignment.entities.UserEntity;
import com.sony.assignment.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private ConversionService conversionService;
	
	@Override
	public UserEntity getUser(long id) {
		return repo.findById(id).orElseThrow(() -> new UserIdNotExists(id));
	}
	
	@Override
	public UserEntity getUser(String email) {
		return repo.findByEmail(email)
				.stream()
				.findFirst()
				.orElseThrow(() -> new UserEmailNotExists(email));
	}
	
	@Override
	public UserEntity[] getAll() {
		return repo.findAll().stream().toArray(UserEntity[]::new);
	}
	
	@Override
	public void deleteUser(long id) {
		repo.deleteById(id);
	}
	
	@Override
	public UserEntity addUser(CreateUserBoundary request) {
		if(repo.findByEmail(request.getEmail()).stream().count() != 0)
			throw new UserAlreadyExists(request.getEmail());
		UserEntity add = conversionService.convert(request, UserEntity.class);
		return repo.save(add);
	}

	@Override
	public UserEntity updateUser(long id, UpdateUserBoundary request) {
		UserEntity current = this.getUser(id);
		if(!current.getEmail().equals(request.getEmail())) {
			if(repo.findByEmail(request.getEmail()).stream().count() != 0)
				throw new UserAlreadyExists(request.getEmail());
		}
		UserEntity update = conversionService.convert(request, UserEntity.class);
		current.update(update);
		this.repo.save(current);
		return current;
	}
	
}
