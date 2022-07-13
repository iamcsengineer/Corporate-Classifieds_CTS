package com.spring.mfpe.offer.exceptions;

//thrown when an employee for a particular id does not exist
public class EmployeeNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public EmployeeNotFoundException(String message) {
		super(message);
	}
}
