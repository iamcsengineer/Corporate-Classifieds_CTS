package com.cts.pointsmicroservice.service;

import com.cts.pointsmicroservice.exception.InvalidUserException;
import com.cts.pointsmicroservice.exception.MicroserviceException;
import com.cts.pointsmicroservice.model.MessageResponse;

public interface PointsService {

	/**retrieve points for a particular employee
	 * @param token
	 * @param employeeId
	 * @return Points
	 * @throws InvalidUserException
	 */
	public Integer getPoints(String token, int id) throws MicroserviceException;

	
	/**
	 * refresh points for a particular employee
	 * if the employee posted an offer and it has 50 likes within 2 days that employee get 10 points
	 * if the employee posted an offer and it has 100 likes within 2 days that employee get 50 points
	 * if the employee posted an offer and it has engaged within 2 days that employee gets 100 points
	 * @param token
	 * @param employeeId
	 * @return Points
	 * @throws MicroserviceException 
	 * @throws InvalidUserException
	 */
	public MessageResponse refreshPoints(String token, int id) throws MicroserviceException, InvalidUserException;

}
