package com.cts.pointsmicroservice.exception;

import java.net.ConnectException;
import java.util.Date;
import java.util.NoSuchElementException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cts.pointsmicroservice.model.MessageResponse;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

//global exception handler for all the exceptions
@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)

public class PointsExceptionHandler extends ResponseEntityExceptionHandler {

	//handles null pointer exception
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<?> handleUserNotFoundException(NullPointerException ce) {
		log.error("Bad request:Employee Details not found");
		return ResponseEntity.badRequest().body(new MessageResponse(new Date(), "Employee Details not Found", HttpStatus.UNAUTHORIZED));
	}

	/**validity of tokenn
	 * @param StringOutOfBoundsException
	 * @return ResponseEntity<MessageResponse>
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(StringIndexOutOfBoundsException.class)
	public ResponseEntity<?> handleStringIndexOutOfBoundException(StringIndexOutOfBoundsException sie) {
		log.error("Bad Request:Not a valid token");
		return ResponseEntity.badRequest().body(new MessageResponse(new Date(),"Not a valid token", HttpStatus.UNAUTHORIZED));

	}

	/**checks for feign servers availability
	 * @param FeignException
	 * @return ResponseEntity<MessageResponse>
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(FeignException.class)
	public ResponseEntity<?> handleFeignException(FeignException fe) {
		log.error("Bad request:Service Unavailable");
		return ResponseEntity.badRequest().body(new MessageResponse(new Date(),"Service Unavailable", HttpStatus.INTERNAL_SERVER_ERROR));

	}

	/**Checks if the entered value by the user is present in the database or not.
	 * If the requested value is not present in the database,then it throws 
	 * EmptyResultDataAccessException with the message id not existing
	 * through the response entity along with the timeStamp and httpStatus
	 * @param EmptyResultDataAccessException
	 * @return ResponseEntity<MessageResponse>
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<?> handleEmptyResultDataAccessException(EmptyResultDataAccessException ere) {
		log.error("Bad request:ID cannot exist");
		return ResponseEntity.badRequest().body(new MessageResponse(new Date(),"ID cannot exist", HttpStatus.INTERNAL_SERVER_ERROR));

	}

	/**Checks if the entered value by the user is present in the database or not.
	 * If the requested value is not present in the database,then it throws 
	 * NoSuchElementException with the message id not present
	 * through the response entity along with the timeStamp and httpStatus
	 * 
	 * @param NoSuchElementException
	 * @return ResponseEntity<MessageResponse>
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<?> handleNoSuchElementException(NoSuchElementException nsee) {
		log.error("Bad request:ID cannot exist");
		return ResponseEntity.badRequest().body(new MessageResponse(new Date(),"ID cannot exist", HttpStatus.UNAUTHORIZED));

	}

	/**Checks if microService required for the running of that particular 
	 * microServices are in running state or not.If any one microService 
	 * is not running,then it throws ConnectException with
	 * the message id not existing through the response entity along with
	 * the timeStamp and httpStatus
	 * @param ConnectException
	 * @return ResponseEntity<MessageResponse>
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ConnectException.class)
	public ResponseEntity<?> handleServiceDownException(ConnectException ce) {
		log.error("Bad request:Check your Connection");
		return ResponseEntity.badRequest().body(new MessageResponse(new Date(),"Check your Connection", HttpStatus.INTERNAL_SERVER_ERROR));

	}
	
	/**invalid user exception handler
	 * @param InvalidUserException
	 * @return ResponseEntity<MessageResponse>
	 */
	@ExceptionHandler(InvalidUserException.class)
	public ResponseEntity<?> handleInvalidUserException(InvalidUserException ie) {
		log.error("Bad request:Invalid User");
		return ResponseEntity.badRequest().body(new MessageResponse(new Date(),ie.getMessage(), HttpStatus.UNAUTHORIZED));

	}
	
	//handles any microservice error
	@ExceptionHandler(MicroserviceException.class)
	public ResponseEntity<MessageResponse> handleMicorserviceError(MicroserviceException ex){
		log.error("microservice error");
		return new ResponseEntity<MessageResponse>(new MessageResponse(new Date(),ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR),HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
