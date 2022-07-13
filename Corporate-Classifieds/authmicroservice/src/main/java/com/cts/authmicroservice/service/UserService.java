package com.cts.authmicroservice.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.cts.authmicroservice.model.AuthResponse;
import com.cts.authmicroservice.model.UserModel;
import com.cts.authmicroservice.model.UserToken;

public interface UserService extends UserDetailsService {

	/**
	 * authenticates the user
	 * @param userModel
	 * @return userToken
	 * @throws UnauthorizedException
	 */
	UserToken login(UserModel userModel);

	
	/**
	 * checks the validity of the JWT token
	 * @param token
	 * @return authResponse
	 */
	AuthResponse getValidity(String token);
	
	
//	/**It returns the employee id of the user who has logged in.
//	 * It is sent along with the token for future uses.
//	 * 
//	 * @param userName
//	 * @return employeeId(integer)
//	 */
//	public int getEmpId(String username);
}
