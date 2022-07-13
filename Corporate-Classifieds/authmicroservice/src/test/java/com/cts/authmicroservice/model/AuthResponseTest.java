package com.cts.authmicroservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AuthResponseTest {

	AuthResponse auth= new AuthResponse();
	AuthResponse auth1= new AuthResponse("ad",1,true);
	
	@Test
	void testUserName() 
	{
		auth.setUsername("a");
		assertEquals(auth.getUsername(), "a");
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

