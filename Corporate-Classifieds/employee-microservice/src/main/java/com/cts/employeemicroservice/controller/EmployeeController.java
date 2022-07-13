package com.cts.employeemicroservice.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.employeemicroservice.exception.InvalidUserException;
import com.cts.employeemicroservice.exception.MicroserviceException;
import com.cts.employeemicroservice.model.Employee;
import com.cts.employeemicroservice.model.EmployeeOffers;
import com.cts.employeemicroservice.model.MessageResponse;
import com.cts.employeemicroservice.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	/**
	 * view offers for an employee
	 * 
	 * @param token
	 * @param employeeId
	 * @return ResponseEntity<List<EmployeeOffers>>
	 * @throws MicroserviceException
	 * @throws InvalidUserException
	 */
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/viewEmployeeOffers/{id}")
	public ResponseEntity<?> viewEmployeeOffers(@RequestHeader(name = "Authorization", required = true) String token,
			@PathVariable("id") int employeeId) throws InvalidUserException, MicroserviceException {
		log.info("Inside view employee offers");
		return new ResponseEntity<>(employeeService.viewEmpOffers(token, employeeId), HttpStatus.OK);
	}

	/**
	 * view the employee details(profile)
	 * 
	 * @param token
	 * @param employeeId
	 * @return ResponseEntity<List<EmployeeOffers>>
	 * @throws MicroserviceException
	 * @throws InvalidUserException
	 */
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/viewProfile/{id}")
	public ResponseEntity<Employee> viewProfile(@RequestHeader(name = "Authorization", required = true) String token,
			@PathVariable("id") int id) throws InvalidUserException, MicroserviceException {
		log.info("Inside view employee profile");
		return new ResponseEntity<>(employeeService.viewEmployee(token, id), HttpStatus.OK);
	}

	/**
	 * view the employee's 3 most liked offers
	 * 
	 * @param token
	 * @param employeeId
	 * @return ResponseEntity<List<EmployeeOffers>>
	 * @throws MicroserviceException
	 * @throws InvalidUserException
	 */
	@GetMapping("/viewMostLikedOffers/{id}")
	public ResponseEntity<?> viewTopOffers(@RequestHeader(name = "Authorization", required = true) String token,
			@PathVariable("id") int id) throws InvalidUserException, MicroserviceException {
		log.info("Inside view top offers");
		return new ResponseEntity<>(employeeService.viewTopOffers(token, id), HttpStatus.OK);
	}

	/**
	 * update the employee points
	 * 
	 * @param token
	 * @param points
	 * @return
	 * @throws InvalidUserException
	 * @throws MicroserviceException
	 */
	@PostMapping("/savePoints/{points}")
	public ResponseEntity<MessageResponse> savePoints(@RequestHeader("Authorization") String token,
			@PathVariable("points") int points) throws InvalidUserException, MicroserviceException {
		log.info("inside save points");
		return new ResponseEntity<>(employeeService.savePoints(token, points), HttpStatus.OK);
	}

	/**
	 * update details of user liked offers
	 * 
	 * @param token
	 * @param offerId
	 * @return
	 * @throws MicroserviceException
	 */
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/likeOffer/{id}")
	public MessageResponse likeOffer(@RequestHeader("Authorization") String token, @PathVariable("id") int offerId)
			throws MicroserviceException {
		return employeeService.likeOffer(token, offerId);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/recentlyLiked")
	public Set<EmployeeOffers> getLikedOffers(@RequestHeader("Authorization") String token)
			throws InvalidUserException, MicroserviceException {
		return employeeService.getLikedOffers(token);
	}
}