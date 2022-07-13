package com.cts.pointsmicroservice.model;

import java.util.Date;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponse {
	Date timeStamp;
	String message;
	HttpStatus status;
}
