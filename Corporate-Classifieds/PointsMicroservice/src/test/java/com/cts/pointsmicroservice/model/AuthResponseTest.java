package com.cts.pointsmicroservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AuthResponseTest {

	AuthResponse auth= new AuthResponse();
	AuthResponse auth1= new AuthResponse(1,"ad",true);
	
	@Test
	void testUserName() 
	{
		auth.setUserName("a");
		assertEquals(auth.getUserName(), "a");
	}
	
	@Test
	void testEmpId() {
		auth.setEmpid(1);
		assertEquals(auth.getEmpid(),1);
	}
	
	@Test
	void testIsValid() {
		auth.setValid(true);
		assertEquals(auth.isValid(), true);
	}
	
	@Test
	void testToString() {
		String string = auth1.toString();
		assertEquals(auth1.toString(), string);
	}
}
