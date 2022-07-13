package com.cts.employeemicroservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;

public class MessageResponseTest {

	MessageResponse messageResponse = new MessageResponse();
	Date date=new Date();
	
	@Test
	void testMessage() {
		messageResponse.setMessage("abc");
		assertEquals(messageResponse.getMessage(), "abc");
	}
	
	@Test
	void testStatus() {
		messageResponse.setMessage("abc");
		assertEquals(messageResponse.getMessage(), "abc");
	}
	
	@Test
	void testTimeStamp() {
		messageResponse.setTimeStamp(date);
		assertEquals(messageResponse.getTimeStamp(),date);
	}
}

