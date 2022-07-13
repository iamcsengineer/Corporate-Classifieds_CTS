package com.cts.employeemicroservice.exception;

import java.net.ConnectException;
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

import com.cts.employeemicroservice.model.MessageResponse;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class EmployeeExceptionHandler extends ResponseEntityExceptionHandler {

	/**Checks the value of the object.If it is null,it throws NullPointerException.
	 * It throws the exception with the message details of employee not found
	 * through the response entity along with the timeStamp and httpStatus
	 * 
	 * @param NullPointerException
	 * @return ResponseEntity<MessageResponse>
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<?> handleUserNotFoundException(NullPointerException ce) {
		log.error("Bad request:Employee Details not found");
		return ResponseEntity.badRequest().body(new MessageResponse("Employee Details not Found",HttpStatus.UNAUTHORIZED));

	}

	/**Checks the value of the authorization token entered by the user.
	 * If any characters are missing in the token,then it throws 
	 * StringOutOfBoundsException with the message not a valid token
	 * through the response entity along with the timeStamp and httpStatus
	 * 
	 * @param StringOutOfBoundsException
	 * @return ResponseEntity<MessageResponse>
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(StringIndexOutOfBoundsException.class)
	public ResponseEntity<?> handleStringIndexOutOfBoundException(StringIndexOutOfBoundsException sie) {
		log.error("Bad Request:Not a valid token");
		return ResponseEntity.badRequest().body(new MessageResponse("Not a valid token", HttpStatus.UNAUTHORIZED));

	}

	/**Checks if all the feign servers required for the particular microService is running or not.
	 * If any one microService is down and running ,then it throws 
	 * FeignException with the message service unavailable
	 * through the response entity along with the timeStamp and httpStatus
	 * 
	 * @param FeignException
	 * @return ResponseEntity<MessageResponse>
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(FeignException.class)
	public ResponseEntity<?> handleFeignException(FeignException fe) {
		log.error("Bad request:Service Unavailable");
		return ResponseEntity.badRequest().body(new MessageResponse("Service Unavailable", HttpStatus.SERVICE_UNAVAILABLE));

	}

	/**Checks if the entered value by the user is present in the database or not.
	 * If the requested value is not present in the database,then it throws 
	 * EmptyResultDataAccessException with the message id not existing
	 * through the response entity along with the timeStamp and httpStatus
	 * 
	 * @param EmptyResultDataAccessException
	 * @return ResponseEntity<MessageResponse>
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<?> handleEmptyResultDataAccessException(EmptyResultDataAccessException ere) {
		log.error("Bad request:Employee ID not exist");
		return ResponseEntity.badRequest().body(new MessageResponse("Employee ID not exist", HttpStatus.NOT_FOUND));

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
		log.error("Bad request:Employee ID not exist");
		return ResponseEntity.badRequest().body(new MessageResponse("Employee ID not exist", HttpStatus.NOT_FOUND));

	}

	/**Checks if microService required for the running of that particular 
	 * microServices are in running state or not.If any one microService 
	 * is not running,then it throws ConnectException with
	 * the message id not existing through the response entity along with
	 * the timeStamp and httpStatus
	 * 
	 * @param ConnectException
	 * @return ResponseEntity<MessageResponse>
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ConnectException.class)
	public ResponseEntity<?> handleServiceDownException(ConnectException ce) {
		log.error("Bad request:Check your Connection");
		return ResponseEntity.badRequest().body(new MessageResponse("Check your Connection", HttpStatus.SERVICE_UNAVAILABLE));

	}
	
	/**Checks the entered user token.If the token is not valid
	 * it throws InvalidUserException with the message InvalidUser
	 * through the response entity along with the timeStamp and httpStatus
	 * 
	 * @param InvalidUserException
	 * @return ResponseEntity<MessageResponse>
	 */
	@ExceptionHandler(InvalidUserException.class)
	public ResponseEntity<?> handleInvalidUserException(InvalidUserException ie) {
		log.error("Bad request:Invalid User");
		return ResponseEntity.badRequest().body(new MessageResponse(ie.getMessage(), HttpStatus.UNAUTHORIZED));
	}
	
	//to handle microservice exceptions
	@ExceptionHandler(MicroserviceException.class)
	public ResponseEntity<MessageResponse> handleMicroserviceException(MicroserviceException ex){
		log.error("error in a microservice");
		return new ResponseEntity<MessageResponse>(new MessageResponse(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR),HttpStatus.INTERNAL_SERVER_ERROR);
	}

}