package com.cts.employeemicroservice.exception;

public class MicroserviceException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MicroserviceException(String message){
		super(message);
	}
}
