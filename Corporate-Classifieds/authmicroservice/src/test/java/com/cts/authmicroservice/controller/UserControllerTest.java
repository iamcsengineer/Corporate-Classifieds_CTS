package com.cts.authmicroservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import com.cts.authmicroservice.jwt.JwtUtil;
import com.cts.authmicroservice.model.AuthResponse;
import com.cts.authmicroservice.model.UserModel;
import com.cts.authmicroservice.model.UserToken;
import com.cts.authmicroservice.repository.UserRepository;
import com.cts.authmicroservice.service.UserServiceImpl;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserControllerTest {
	
	@InjectMocks
	UserController userController;

	AuthResponse authResponse;

	UserDetails userdetails;

	@Mock
	JwtUtil jwtutil;

	@Mock
	UserServiceImpl userServiceImpl;

	@Mock
	UserRepository userRepository;
	
	@Test
	public void loginTest()
	{
		UserModel userLoginCredentials = new UserModel(1,"admin","admin");
		UserToken userToken= new UserToken("token","admin",1);
		when(userServiceImpl.login(userLoginCredentials)).thenReturn(userToken);
		assertEquals(userController.login(userLoginCredentials).getBody(),userToken);
	}

	@Test
	public void validityTest()
	{
		AuthResponse auth = new AuthResponse("admin", 1, true);
		ResponseEntity<AuthResponse> response = new ResponseEntity<>(auth,HttpStatus.OK);
		when(userServiceImpl.getValidity("bearer token")).thenReturn(auth);
		assertEquals(userController.getValidity("bearer token"),response);
	}
	
}