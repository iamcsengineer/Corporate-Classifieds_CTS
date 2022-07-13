package com.cts.pointsmicroservice.exception;

//thrown when an error occurs in a different microservice
public class MicroserviceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MicroserviceException(String message){
		super(message);
	}
}
