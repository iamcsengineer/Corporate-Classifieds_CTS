package com.cts.employeemicroservice.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import com.cts.employeemicroservice.exception.InvalidUserException;
import com.cts.employeemicroservice.exception.MicroserviceException;
import com.cts.employeemicroservice.model.Employee;
import com.cts.employeemicroservice.model.EmployeeOffers;
import com.cts.employeemicroservice.model.MessageResponse;

public interface EmployeeService {

	/**
	 * view employee offers
	 * @param token
	 * @param employeeId
	 * @return List<EmployeeOffers>
	 * @throws InvalidUserException
	 */
	public List<EmployeeOffers> viewEmpOffers(String token,int employeeId) throws MicroserviceException,InvalidUserException;
	
	/**
	 * view top 3 offers of the employee
	 * @param token
	 * @param employeeId
	 * @return List<EmployeeOffers>
	 * @throws MicroserviceException 
	 * @throws InvalidUserException
	 */
	public List<EmployeeOffers> viewTopOffers(String token,int employeeId) throws MicroserviceException, InvalidUserException;
	
	
	/**
	 * view employee details
	 * @param token
	 * @param id
	 * @return Employee
	 * @throws MicroserviceException,InvalidUserException 
	 * @throws InvalidUserException
	 * @throws NoSuchElementException
	 */
	public Employee viewEmployee(String token,int id) throws MicroserviceException,InvalidUserException;

	public MessageResponse savePoints(String token, int points) throws MicroserviceException, InvalidUserException;

	public MessageResponse likeOffer(String token, int offerId) throws MicroserviceException;

	public Set<EmployeeOffers> getLikedOffers(String token) throws MicroserviceException,InvalidUserException;
	
}
