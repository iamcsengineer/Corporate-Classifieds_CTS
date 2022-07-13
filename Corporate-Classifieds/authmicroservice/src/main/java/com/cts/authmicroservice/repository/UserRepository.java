package com.cts.authmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.authmicroservice.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, String>{
	
	//to find a user by its user name
	public UserModel findByEmpUsername(String username);
}

