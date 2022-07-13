package com.spring.mfpe.offer.exceptions;

//thrown when user passes improper date
public class ImproperDateException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ImproperDateException(String message){
		super(message);
	}
}
