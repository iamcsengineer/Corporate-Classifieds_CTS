package com.cts.authmicroservice.exception;

//thrown when the user is unauthorized
public class UnauthorizedException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnauthorizedException(String message) {
		super(message);
	}
}
