package com.spring.mfpe.offer.model;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//model for response entity ( check global exception handler)
@Component
@Setter
@Getter
@NoArgsConstructor
public class ErrorResponse {
	private String message;
	private HttpStatus status;
	private Date timestamp;	
}
