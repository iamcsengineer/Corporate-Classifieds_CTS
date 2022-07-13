package com.spring.mfpe.offer.exceptions;

//thrown when there is an error in a different microservice
public class MicroserviceException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MicroserviceException(String message) {
		super(message);
	}
}
