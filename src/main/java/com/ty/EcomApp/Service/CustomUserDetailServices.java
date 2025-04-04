package com.ty.EcomApp.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ty.EcomApp.Model.CustomUserDetail;
import com.ty.EcomApp.Model.User;
import com.ty.EcomApp.Repository.UserRepository;

@Service
public class CustomUserDetailServices implements UserDetailsService{

	private final UserRepository repository;
	
	public CustomUserDetailServices(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByUsername(username);
		if(user==null) {
			System.out.println("user not found!");
			throw new UsernameNotFoundException("User not Found");
		}
		return new CustomUserDetail(user);
	}

}
