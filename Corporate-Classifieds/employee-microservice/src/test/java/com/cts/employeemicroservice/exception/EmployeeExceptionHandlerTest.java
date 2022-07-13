package com.cts.employeemicroservice.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.ConnectException;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import feign.FeignException;

@SpringBootTest
public class EmployeeExceptionHandlerTest {

	@InjectMocks
	EmployeeExceptionHandler employeeExceptionHandler;
	
	FeignException feignException;
	
	@Test
	public void handleUserNotFoundExceptionTest() {
		assertEquals(employeeExceptionHandler.handleUserNotFoundException(new NullPointerException(null)).getStatusCodeValue(), 400);
	}
	
	@Test
	public void handleStringIndexOutOfBoundExceptionTest() {
		assertEquals(employeeExceptionHandler.handleStringIndexOutOfBoundException(new StringIndexOutOfBoundsException(null)).getStatusCodeValue(), 400);
	}

	@Test
	public void handleFeignExceptionTest() {
		assertEquals(employeeExceptionHandler.handleFeignException(feignException).getStatusCodeValue(), 400);
	}
	
	@Test
	public void handleEmptyResultDataAccessExceptionTest() {
		assertEquals(employeeExceptionHandler.handleEmptyResultDataAccessException(new EmptyResultDataAccessException(1)).getStatusCodeValue(), 400);
	}
	
	@Test
	public void handleNoSuchElementExceptionTest() {
		assertEquals(employeeExceptionHandler.handleNoSuchElementException(new NoSuchElementException()).getStatusCodeValue(), 400);
	}

	@Test
	public void handleServiceDownExceptionTest() {
		assertEquals(employeeExceptionHandler.handleServiceDownException(new ConnectException(null)).getStatusCodeValue(), 400);
	}
	
	@Test
	public void handleInvalidUserExceptionTest() {
		assertEquals(employeeExceptionHandler.handleInvalidUserException(new InvalidUserException(null)).getStatusCodeValue(), 400);
	}

}
