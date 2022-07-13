package com.spring.mfpe.offer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import com.spring.mfpe.offer.exceptions.EmployeeNotFoundException;
import com.spring.mfpe.offer.exceptions.GlobalExceptionHandler;
import com.spring.mfpe.offer.exceptions.ImproperDateException;
import com.spring.mfpe.offer.exceptions.InvalidTokenException;
import com.spring.mfpe.offer.exceptions.MicroserviceException;
import com.spring.mfpe.offer.exceptions.OfferAlreadyEngagedException;
import com.spring.mfpe.offer.exceptions.OfferNotFoundException;

public class ExceptionTests {
	
	GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
	
	@Test
	public void testEmployeeNotFoundException() {
		assertEquals(globalExceptionHandler.employeeNotFound(new EmployeeNotFoundException(null)).getStatusCode(),HttpStatus.NOT_FOUND);
	}
	
	@Test
	public void testOfferNotFoundException() {
		assertEquals(globalExceptionHandler.offerNotFound(new OfferNotFoundException(null)).getStatusCode(),HttpStatus.NOT_FOUND);
	}
	
	@Test
	public void testImproperDateException() {
		assertEquals(globalExceptionHandler.improperDate(new ImproperDateException(null)).getStatusCode(),HttpStatus.BAD_REQUEST);
	}
	
	@Test 
	void testOfferAlreadyEngagedException() {
		assertEquals(globalExceptionHandler.offerEngaged(new OfferAlreadyEngagedException(null)).getStatusCode(),HttpStatus.BAD_REQUEST);
	}
	
	@Test
	void testInvalidTokenException() {
		assertEquals(globalExceptionHandler.invalidToken(new InvalidTokenException(null)).getStatusCode(),HttpStatus.UNAUTHORIZED);
	}
	
	@Test
	void testMicroserviceException() {
		assertEquals(globalExceptionHandler.microserviceError(new MicroserviceException(null)).getStatusCode(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
