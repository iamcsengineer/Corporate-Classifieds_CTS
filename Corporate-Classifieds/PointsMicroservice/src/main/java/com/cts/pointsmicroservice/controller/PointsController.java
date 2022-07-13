package com.cts.pointsmicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cts.pointsmicroservice.exception.InvalidUserException;
import com.cts.pointsmicroservice.exception.MicroserviceException;
import com.cts.pointsmicroservice.service.PointsService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class PointsController {

	@Autowired
	PointsService pointsService;

	/**
	 * get points gained by a particular employee by employee id
	 * 
	 * @param token
	 * @param employeeId
	 * @return Points
	 * @throws MicroserviceException
	 * @throws InvalidUserException
	 */
	@GetMapping("/getpointsbyemp/{id}")
	public ResponseEntity<Integer> getPointsByEmpId(@RequestHeader(name = "Authorization") String token,
			@PathVariable("id") int id) throws MicroserviceException {
		log.info("Inside getpointsbyemployeeid of points microservice");
		return new ResponseEntity<>(pointsService.getPoints(token, id), HttpStatus.OK);
	}

	/**
	 * update the employee's points gained if the employee posted an offer and it
	 * has 50 likes within 2 days that employee gets 10 points if the employee
	 * posted an offer and it has 100 likes within 2 days that employee gets 50
	 * points if the employee posted and offer and it engaged within 2 days that
	 * employee gets 100 points
	 * 
	 * @param token
	 * @param employeeId
	 * @return Points
	 * @throws MicroserviceException
	 * @throws InvalidUserException
	 */
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/refreshpointsbyemp/{id}")
	public ResponseEntity<?> refreshPointsByEmpId(@RequestHeader(name = "Authorization") String token,
			@PathVariable("id") int id) throws InvalidUserException, MicroserviceException {
		log.info("Inside refreshpoints of points microservice");
		return new ResponseEntity<>(pointsService.refreshPoints(token, id), HttpStatus.OK);
	}

}
