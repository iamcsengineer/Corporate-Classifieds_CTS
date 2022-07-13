package com.cts.authmicroservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UserModelTest {

	UserModel user = new UserModel();
	UserModel user1 = new UserModel(1, "ad", "ad");

	@Test
	void testEmpId() {
		user.setEmpid(1);
		assertEquals(user.getEmpid(), 1);
	}
	
	@Test
	void testEmpUserName() {
		user.setEmpUsername("Username");
		assertEquals(user.getEmpUsername(), "Username");
	}

	@Test
	void testEmpPassword() {
		user.setEmpPassword("Password");
		assertEquals(user.getEmpPassword(), "Password");
	}

	@Test
	void testToString() {
		String string = user1.toString();
		assertEquals(user1.toString(), string);
	}
}
