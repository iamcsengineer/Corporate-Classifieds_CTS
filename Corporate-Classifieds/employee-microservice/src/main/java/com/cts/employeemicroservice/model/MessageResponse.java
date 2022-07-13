package com.cts.employeemicroservice.model;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class MessageResponse {

	Date timeStamp;
	String message;
	HttpStatus status;
	
	public MessageResponse(String message, HttpStatus status) {
		super();
		this.message = message;
		this.timeStamp=new Date();
		this.status=status;
	}
	}
