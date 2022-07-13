package com.cts.employeemicroservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AuthResponseTest {

	AuthResponse authResponse=new AuthResponse();
	AuthResponse auth=new AuthResponse(1,"admin",true);
	
	@Test
	void testEmpid()
	{
		authResponse.setEmpid(1);
		assertEquals(authResponse.getEmpid(),1);
	}
	
	@Test
	void testUserName()
	{
		authResponse.setUserName("admin");
		assertEquals(authResponse.getUserName(),"admin");
	}
	
	@Test
	void testIsValid()
	{
		authResponse.setValid(true);
		assertEquals(authResponse.isValid(),true);
	}
	
	@Test
	void testToString() {
		String string = auth.toString();
		assertEquals(auth.toString(), string);
	}
}

