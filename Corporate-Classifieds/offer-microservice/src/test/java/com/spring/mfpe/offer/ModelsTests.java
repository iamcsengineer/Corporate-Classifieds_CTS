package com.spring.mfpe.offer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.spring.mfpe.offer.model.AuthResponse;
import com.spring.mfpe.offer.model.ErrorResponse;
import com.spring.mfpe.offer.model.SuccessResponse;

@SpringBootTest
public class ModelsTests {

	@InjectMocks
	AuthResponse authResponse;
	
	@InjectMocks
	ErrorResponse errorResponse;
	
	@InjectMocks
	SuccessResponse successResponse;
	
	//authresponse test
	@Test
	public void testUsername() {
		authResponse.setUsername("prateek");
		assertEquals(authResponse.getUsername(),"prateek");
	}
	
	@Test
	public void testEmpId() {
		authResponse.setEmpid(2);
		assertEquals(authResponse.getEmpid(),2);
	}
	
	@Test
	public void testValid() {
		authResponse.setValid(true);
		assertEquals(authResponse.isValid(),true);
	}
	
	//Error response
	@Test
	public void testMessage() {
		errorResponse.setMessage("new message");
		assertEquals(errorResponse.getMessage(),"new message");
	}
	
	@Test
	public void testStatus() {
		errorResponse.setStatus(HttpStatus.ACCEPTED);
		assertEquals(errorResponse.getStatus(),HttpStatus.ACCEPTED);
	}
	
	@Test
	public void testTimestamp() {
		errorResponse.setTimestamp(new Date());
		assertEquals(errorResponse.getTimestamp(),new Date());
	}
	
	//Success response
	@Test
	public void testMessageSuccess() {
		successResponse.setMessage("new message");
		assertEquals(successResponse.getMessage(),"new message");
	}
	
	@Test
	public void testStatusSuccess() {
		successResponse.setStatus(HttpStatus.ACCEPTED);
		assertEquals(successResponse.getStatus(),HttpStatus.ACCEPTED);
	}
	
	@Test
	public void testTimestampSuccess() {
		successResponse.setTimestamp(new Date());
		assertEquals(successResponse.getTimestamp(),new Date());
	}
}
