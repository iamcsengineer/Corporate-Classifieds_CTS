package com.spring.mfpe.offer.exceptions;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.spring.mfpe.offer.model.ErrorResponse;

import lombok.NoArgsConstructor;

//global exception handler for all the generated exceptions
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	// to handle offer not found exception
	@ExceptionHandler(OfferNotFoundException.class)
	public ResponseEntity<ErrorResponse> offerNotFound(OfferNotFoundException ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setStatus(HttpStatus.NOT_FOUND);
		errorResponse.setTimestamp(new Date());

		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}

	// to handle improper date format ( check getOfferByPostedDate)
	@ExceptionHandler(ImproperDateException.class)
	public ResponseEntity<ErrorResponse> improperDate(ImproperDateException ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setStatus(HttpStatus.BAD_REQUEST);
		errorResponse.setTimestamp(new Date());

		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	// to handle employee not found
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<ErrorResponse> employeeNotFound(EmployeeNotFoundException ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setStatus(HttpStatus.NOT_FOUND);
		errorResponse.setTimestamp(new Date());

		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}

	// to handle offer already engaged
	@ExceptionHandler(OfferAlreadyEngagedException.class)
	public ResponseEntity<ErrorResponse> offerEngaged(OfferAlreadyEngagedException ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setStatus(HttpStatus.BAD_REQUEST);
		errorResponse.setTimestamp(new Date());

		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	//to handle invalid token
	@ExceptionHandler(InvalidTokenException.class)
	public ResponseEntity<ErrorResponse> invalidToken(InvalidTokenException ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setStatus(HttpStatus.UNAUTHORIZED);
		errorResponse.setTimestamp(new Date());

		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.UNAUTHORIZED);
	}
	
	//to handle microservice error
	@ExceptionHandler(MicroserviceException.class)
	public ResponseEntity<ErrorResponse> microserviceError(MicroserviceException ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		errorResponse.setTimestamp(new Date());

		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
