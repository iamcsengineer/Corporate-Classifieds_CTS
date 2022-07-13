package com.spring.mfpe.offer.exceptions;

//thrown when an offer is not found
public class OfferNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	public OfferNotFoundException(String message) {
		super(message);
	}
 
}
