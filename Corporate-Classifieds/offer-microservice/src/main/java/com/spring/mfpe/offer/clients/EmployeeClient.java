package com.spring.mfpe.offer.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.mfpe.offer.entities.Employee;
import com.spring.mfpe.offer.model.AuthResponse;

//connecting to authentication micro-service
@FeignClient(url = "${employee.feign.client}", name = "${employee.feign.name}")
public interface EmployeeClient {
	
	/**
	 * returns the employee
	 * @param token
	 * @return
	 */
	@RequestMapping(path = "/viewProfile/{id}", method = RequestMethod.GET)
	public Employee getEmployee(@RequestHeader(name = "Authorization", required = true) String token, @PathVariable("id") int id);
}
