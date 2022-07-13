package com.spring.mfpe.offer.model;

import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Setter
@Getter
@NoArgsConstructor
public class SuccessResponse {
	private String message;
	private HttpStatus status;
	private Date timestamp;
}
