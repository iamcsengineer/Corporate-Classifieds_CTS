package com.cts.authmicroservice.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cts.authmicroservice.model.MessageResponse;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;

//global exception handler
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler {

	/**
	 * to handle unauthorized exception
	 * @param UnauthorizedException
	 * @return ResponseEntity<MessageResponse>
	 */
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<?> handleUnauthorizedExceptions(UnauthorizedException ex) {
		log.error("Unauthorized request...");
		return ResponseEntity.badRequest()
				.body(new MessageResponse("Unauthorized request. Login again...", HttpStatus.UNAUTHORIZED));
	}

	
	/**
	 * handles exception when authorization token is missing
	 * @param MissingRequestHeaderException
	 * @return ResponseEntity<MessageResponse>
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MissingRequestHeaderException.class)
	public ResponseEntity<?> handleMissingRequestHeaderException(MissingRequestHeaderException ex) {
		log.error("Required Bearer token....");
		return ResponseEntity.badRequest().body(new MessageResponse("Required Bearer token", HttpStatus.BAD_REQUEST));
	}

	
	/**Checks header of the request that the user enters.
	 * handles expires JWT token
	 * @param ExpiredJwtExceptionException
	 * @return ResponseEntity<MessageResponse>
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity<?> handleExpiredJwtException(ExpiredJwtException ex) {

		log.error("Token has expired");
		return ResponseEntity.badRequest().body(new MessageResponse("Token has expired", HttpStatus.BAD_REQUEST));
	}
}
