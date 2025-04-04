package com.ty.EcomApp.Repository;

import org.springframework.data.repository.CrudRepository;

import com.ty.EcomApp.Model.User;

public interface UserRepository extends CrudRepository<User,Integer> {
	
	 public User findByUsername(String name);
	

}
