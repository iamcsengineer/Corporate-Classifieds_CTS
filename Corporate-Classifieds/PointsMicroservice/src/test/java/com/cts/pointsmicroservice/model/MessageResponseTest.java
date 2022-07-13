package com.cts.pointsmicroservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class MessageResponseTest {

	MessageResponse messageResponse = new MessageResponse();
	MessageResponse msgResponse = new MessageResponse(new Date(),"abc",HttpStatus.OK);
	MessageResponse msgRes = new MessageResponse(new Date(), "abc",HttpStatus.OK);
	Date date=new Date();
	
	@Test
	void testMessage() {
		messageResponse.setMessage("abc");
		assertEquals(messageResponse.getMessage(), "abc");
	}
	
	@Test
	void testStatus() {
		messageResponse.setStatus(HttpStatus.OK);
		assertEquals(messageResponse.getStatus(), HttpStatus.OK);
	}
	
	@Test
	void testTimeStamp() {
		messageResponse.setTimeStamp(date);
		assertEquals(messageResponse.getTimeStamp(),date);
	}
}
