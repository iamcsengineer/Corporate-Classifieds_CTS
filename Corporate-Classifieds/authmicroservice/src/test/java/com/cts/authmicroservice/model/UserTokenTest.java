package com.cts.authmicroservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UserTokenTest {

	UserToken userToken = new UserToken();
	UserToken userToken1 = new UserToken("ad","ad",1);
	
	@Test
	void testUserName() {
		userToken.setUsername("a");
		assertEquals(userToken.getUsername(), "a");
	}
	
	@Test
	void testAuthToken() {
		userToken.setAuthToken("a");
		assertEquals(userToken.getAuthToken(), "a");
	}
	
	@Test
	void testEmpId() {
		userToken.setEmpid(1);
		assertEquals(userToken.getEmpid(), 1);
	}
}