package com.cts.pointsmicroservice.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.ConnectException;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import feign.FeignException;

@SpringBootTest
public class PointsExceptionHandlerTest {

	@InjectMocks
	PointsExceptionHandler pointsExceptionHandler;
	
	FeignException feignException;
	
	@Test
	public void handleUserNotFoundExceptionTest() {
		assertEquals(pointsExceptionHandler.handleUserNotFoundException(new NullPointerException(null)).getStatusCodeValue(), 400);
	}
	
	@Test
	public void handleStringIndexOutOfBoundExceptionTest() {
		assertEquals(pointsExceptionHandler.handleStringIndexOutOfBoundException(new StringIndexOutOfBoundsException(null)).getStatusCodeValue(), 400);
	}

	@Test
	public void handleFeignExceptionTest() {
		assertEquals(pointsExceptionHandler.handleFeignException(feignException).getStatusCodeValue(), 400);
	}
	
	@Test
	public void handleEmptyResultDataAccessExceptionTest() {
		assertEquals(pointsExceptionHandler.handleEmptyResultDataAccessException(new EmptyResultDataAccessException(1)).getStatusCodeValue(), 400);
	}
	
	/*@Test
	public void handleNoSuchElementExceptionTest() {
		assertEquals(pointsExceptionHandler.handleNoSuchElementException(new NoSuchElementException(null)).getStatusCodeValue(), 400);
	}
	*/
	@Test
	public void handleServiceDownExceptionTest() {
		assertEquals(pointsExceptionHandler.handleServiceDownException(new ConnectException(null)).getStatusCodeValue(), 400);
	}
	
	@Test
	public void handleInvalidUserExceptionTest() {
		assertEquals(pointsExceptionHandler.handleInvalidUserException(new InvalidUserException(null)).getStatusCodeValue(), 400);
	}
}
