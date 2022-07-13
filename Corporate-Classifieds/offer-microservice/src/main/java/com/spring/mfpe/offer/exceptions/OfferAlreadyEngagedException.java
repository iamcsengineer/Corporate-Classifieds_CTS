package com.spring.mfpe.offer.exceptions;

//thrown when the offer is already engaged
public class OfferAlreadyEngagedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OfferAlreadyEngagedException(String message) {
		super(message);
	}

}
