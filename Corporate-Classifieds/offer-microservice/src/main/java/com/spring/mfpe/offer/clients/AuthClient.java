package com.spring.mfpe.offer.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.mfpe.offer.model.AuthResponse;

//connecting to authentication micro-service
@FeignClient(url = "${auth.feign.client}", name = "${auth.feign.name}")
public interface AuthClient {
	
	/**
	 * validates the jwt token
	 * @param token
	 * @return
	 */
	@RequestMapping(path = "/validate", method = RequestMethod.GET)
	public ResponseEntity<AuthResponse> verifyToken(@RequestHeader(name = "Authorization", required = true) String token);
}
