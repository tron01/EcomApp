package com.ty.EcomApp.Service;

import org.springframework.stereotype.Service;

import com.ty.EcomApp.Model.User;
import com.ty.EcomApp.Repository.UserRepository;

@Service
public class UserService {

	private final UserRepository repository;
	
	public UserService(UserRepository repository) {
		this.repository = repository;
	}

	public User create_user(User user) {
		user.setRoles("USER");
		return repository.save(user);	
	}

	
}
