package com.cts.pointsmicroservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cts.pointsmicroservice.model.MessageResponse;

//to connect to authentication microservice
@FeignClient(url = "${employee.feign.client}", name = "${employee.feign.name}")
public interface EmployeeClient {
	@RequestMapping(path = "/savePoints/{id}", method = RequestMethod.POST)
	public ResponseEntity<MessageResponse> savePoints(
			@RequestHeader(name = "Authorization", required = true) String token,@PathVariable("id") int id);

}
