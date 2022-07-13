package com.cts.authmicroservice.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.cts.authmicroservice.exception.UnauthorizedException;
import com.cts.authmicroservice.jwt.JwtUtil;
import com.cts.authmicroservice.model.AuthResponse;
import com.cts.authmicroservice.model.UserModel;
import com.cts.authmicroservice.model.UserToken;
import com.cts.authmicroservice.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j

public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	JwtUtil jwtUtil;

	@Override
	public UserDetails loadUserByUsername(String userName) {
		// all actions to check the correctness of the userID
		log.info("Inside loadbyusername");
		UserModel user = userRepository.findByEmpUsername(userName);
		return new User(user.getEmpUsername(), user.getEmpPassword(), new ArrayList<>());
	}

	// authenticates the user
	public UserToken login(UserModel userModel) {

		final UserDetails userdetails = loadUserByUsername(userModel.getEmpUsername());

		UserToken userToken = new UserToken();

		// if the password matches
		if (userdetails.getPassword().equals(userModel.getEmpPassword())) {
			log.info("authentication successfull.. Generating token..");

			// set the values for the token
			userToken.setUsername(userModel.getEmpUsername());
			userToken.setEmpid(userRepository.findByEmpUsername(userModel.getEmpUsername()).getEmpid());
			userToken.setAuthToken(jwtUtil.generateToken(userdetails));

			return userToken;
		} else {
			log.error("authentication failed");
			throw new UnauthorizedException("Invalid username or password");
		}
	}

	// validates the JWT token
	public AuthResponse getValidity(String token) {
		// retrieving the token ( removing the Bearer from the header)
		String token1 = token.substring(7);
		AuthResponse authResponse = new AuthResponse();
		// if valid
		if (jwtUtil.validateToken(token1)) {
			log.info("Token is valid");

			// extract the user name
			String username = jwtUtil.extractUsername(token1);

			// set the values for the response
			authResponse.setUsername(username);
			authResponse.setValid(true);
			authResponse.setEmpid(userRepository.findByEmpUsername(username).getEmpid());
		} else {
			authResponse.setValid(false);
			log.error("Token is invalid or expired...");
		}

		return authResponse;
	}

//	public int getEmpId(String username) {
//		log.info("Inside getemployeeid");
//		return userRepository.findByEmpUsername(username).getEmpid();
//	}
}
