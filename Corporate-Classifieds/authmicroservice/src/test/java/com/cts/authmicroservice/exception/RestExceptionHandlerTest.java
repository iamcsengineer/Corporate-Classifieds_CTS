package com.cts.authmicroservice.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.MissingRequestHeaderException;

import io.jsonwebtoken.ExpiredJwtException;

@SpringBootTest
//@RunWith(MockitoJUnitRunner.class)
public class RestExceptionHandlerTest {

	@InjectMocks
	RestExceptionHandler restExceptionHandler;
	
	@Test
	public void handleUnauthorizedExceptionsTest() {
		assertEquals(restExceptionHandler.handleUnauthorizedExceptions(new UnauthorizedException(null)).getStatusCodeValue(), 400);
	}
	
	@Test
	public void handleMissingRequestHeaderExceptionsTest()
	{
		assertEquals(restExceptionHandler.handleMissingRequestHeaderException(new MissingRequestHeaderException(null, null)).getStatusCodeValue(),400);
	}
	
	@Test
	public void handleExpiredJwtExceptionTest()
	{
		assertEquals(restExceptionHandler.handleExpiredJwtException(new ExpiredJwtException(null, null, null, null)).getStatusCodeValue(),400);
	}
}
