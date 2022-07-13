package com.cts.authmicroservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import com.cts.authmicroservice.exception.UnauthorizedException;
import com.cts.authmicroservice.jwt.JwtUtil;
import com.cts.authmicroservice.model.AuthResponse;
import com.cts.authmicroservice.model.UserModel;
import com.cts.authmicroservice.model.UserToken;
import com.cts.authmicroservice.repository.UserRepository;

@SpringBootTest
public class UserServiceImplTest {

	@InjectMocks
	UserServiceImpl userServiceImpl;
	
	@Mock
	UserRepository userRepository;
	
	@Mock
	JwtUtil jwtUtil;

//	@Test
//	public void loadUserByUsernameTest() {
//
//		UserModel user1 = new UserModel(1, "admin", "admin");
//		Optional<UserModel> data = Optional.of(user1);
//		when(userRepository.findById("admin")).thenReturn(data);
//		UserDetails loadUserByUsername2 = userServiceImpl.loadUserByUsername("admin");
//		assertEquals(user1.getEmpUsername(), loadUserByUsername2.getUsername());
//	}

//	@Test
//	public void loginTest() {
//
//		UserModel user = new UserModel(1, "admin", "admin");
//		UserToken userToken=new UserToken("admin","admin",1);
//		Optional<UserModel> data = Optional.of(user);
//		when(userRepository.findById("admin")).thenReturn(data);
//		UserDetails value = userServiceImpl.loadUserByUsername("admin");
//		when(userRepository.findByEmpUsername("admin")).thenReturn(user);
//		when(jwtUtil.generateToken(value)).thenReturn("token");
//		UserToken result = userServiceImpl.login(new UserModel(1,"admin","admin"));
//		assertEquals(result.getUsername(),userToken.getUsername());
//
//	}
//
//	@Test 
//	public void testGetValidity() 
//	{
//	  UserModel user = new UserModel(1, "admin", "admin");
//	  Optional<UserModel> data = Optional.of(user);
//	  when(jwtUtil.validateToken("token")).thenReturn(true);
//	  when(userRepository.findById(jwtUtil.extractUsername("token"))).thenReturn(data);
//	  AuthResponse auth = new AuthResponse("admin",1,true); 
//	  ResponseEntity<AuthResponse> response = new ResponseEntity<AuthResponse>(auth,HttpStatus.OK);
//	  AuthResponse result =userServiceImpl.getValidity("Bearer token");
//	  assertEquals(result.getUsername(), response.getBody().getUsername()); 
//	  }
	
	
	  @Test
	  public void testNotValidate()
	  { 
		  when(jwtUtil.validateToken("Bearer token")).thenReturn(false);
		  AuthResponse result=userServiceImpl.getValidity("Bearer token");
		  assertEquals(result.isValid(), false);
	}
	 

}
