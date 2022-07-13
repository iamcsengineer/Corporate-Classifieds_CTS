package com.spring.mfpe.offer.services;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring.mfpe.offer.clients.AuthClient;
import com.spring.mfpe.offer.clients.EmployeeClient;
import com.spring.mfpe.offer.entities.Employee;
import com.spring.mfpe.offer.entities.Offer;
import com.spring.mfpe.offer.exceptions.EmployeeNotFoundException;
import com.spring.mfpe.offer.exceptions.ImproperDateException;
import com.spring.mfpe.offer.exceptions.InvalidTokenException;
import com.spring.mfpe.offer.exceptions.MicroserviceException;
import com.spring.mfpe.offer.exceptions.OfferAlreadyEngagedException;
import com.spring.mfpe.offer.exceptions.OfferNotFoundException;
import com.spring.mfpe.offer.model.AuthResponse;
import com.spring.mfpe.offer.model.SuccessResponse;
import com.spring.mfpe.offer.repositories.EmployeeRepository;
import com.spring.mfpe.offer.repositories.OfferRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OfferService {

	@Autowired
	OfferRepository offerRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	SuccessResponse successResponse;

	@Autowired
	AuthClient authClient;

	@Autowired
	EmployeeClient employeeClient;

	/**
	 * service that returns offer details for a specific offerId
	 * 
	 * @param offerId
	 * @return
	 * @throws OfferNotFoundException
	 * @throws MicroserviceException
	 */
	public Offer getOfferDetails(String token, int offerId)
			throws OfferNotFoundException, InvalidTokenException, MicroserviceException {
		// authenticate the user
		ResponseEntity<AuthResponse> response;
		try {
			response = authClient.verifyToken(token);
		} catch (Exception e) {
			log.info("some error in auth microservice");
			throw new MicroserviceException(e.getMessage());
		}
		// check if token is valid
		if (response.getBody().isValid()) {
			Optional<Offer> offer = offerRepository.findById(offerId);
			// if offer is not found
			if (!offer.isPresent())
				throw new OfferNotFoundException("No offer found");

			return offer.get();
		}
		// if token is invalid
		else {
			throw new InvalidTokenException("token is invalid");
		}
	}

	/**
	 * service to filter offers by category
	 * 
	 * @param category
	 * @return
	 * @throws OfferNotFoundException
	 * @throws MicroserviceException
	 */
	public List<Offer> getOfferByCategory(String token, String category)
			throws OfferNotFoundException, MicroserviceException, InvalidTokenException {

		// authenticate the user
		ResponseEntity<AuthResponse> response;
		try {
			response = authClient.verifyToken(token);
		} catch (Exception e) {
			log.info("some error in auth microservice");
			throw new MicroserviceException(e.getMessage());
		}

		// if valid
		if (response.getBody().isValid()) {
			List<Offer> offers = offerRepository.findByCategory(category);
			if (offers.size() == 0) {
				throw new OfferNotFoundException("no offers found");
			}
			return offers;
		} else {
			log.info("invalid or expired token");
			throw new InvalidTokenException("token in invalid");
		}
	}

	/**
	 * returns top 3 liked offers
	 * 
	 * @return
	 * @throws OfferNotFoundException
	 * @throws MicroserviceException
	 */
	public List<Offer> getOfferByTopLikes(String token)
			throws OfferNotFoundException, MicroserviceException, InvalidTokenException {

		// authenticate the user
		ResponseEntity<AuthResponse> response;
		try {
			response = authClient.verifyToken(token);
		} catch (Exception e) {
			log.info("some error in auth microservice");
			throw new MicroserviceException(e.getMessage());
		}

		// check if token is valid
		if (response.getBody().isValid()) {
			List<Offer> offers = offerRepository.findAll(PageRequest.of(0, 3, Sort.by("likes").descending()));

			if (offers.size() == 0) {
				throw new OfferNotFoundException("no offers found");
			}
			return offers;
		} else {
			log.info("invalid or expired token");
			throw new InvalidTokenException("token is invalid");
		}
	}

	/**
	 * filter offers by postedDate
	 * 
	 * @param date
	 * @return
	 * @throws OfferNotFoundException
	 * @throws ImproperDateException
	 * @throws MicroserviceException
	 */
	public List<Offer> getOfferByPostedDate(String token, String date)
			throws OfferNotFoundException, ImproperDateException, MicroserviceException, InvalidTokenException {

		ResponseEntity<AuthResponse> response;
		LocalDate currentDate = null;

		// authenticate the user
		try {
			response = authClient.verifyToken(token);
		} catch (Exception e) {
			log.info("some error in auth microservice");
			throw new MicroserviceException(e.getMessage());
		}

		// check if token is valid
		if (response.getBody().isValid()) {
			// if the user passes improper date (proper format yyyy-mm-dd)
			try {
				currentDate = LocalDate.parse(date);
			} catch (Exception e) {
				throw new ImproperDateException("enter a valid date");
			}

			int month = currentDate.getMonthValue();
			int year = currentDate.getYear();
			int day = currentDate.getDayOfMonth();

			// retrieve the offers
			List<Offer> offers = offerRepository.getByPostedDate(month, year, day);

			// if empty
			if (offers.size() == 0)
				throw new OfferNotFoundException("no offers found");

			return offers;
		} else {
			log.info("token invalid or expired");
			throw new InvalidTokenException("token is invalid");
		}
	}

	/**
	 * service to initiate an engagement between the employee and the buyer
	 * 
	 * @param offerId
	 * @param employeeId
	 * @return
	 * @throws OfferNotFoundException
	 * @throws OfferAlreadyEngagedException
	 * @throws EmployeeNotFoundException
	 * @throws MicroserviceException
	 */
	public SuccessResponse engageOffer(String token, int offerId, int employeeId) throws OfferNotFoundException,
			OfferAlreadyEngagedException, EmployeeNotFoundException, MicroserviceException, InvalidTokenException {

		ResponseEntity<AuthResponse> response;

		// authenticate the user
		try {
			response = authClient.verifyToken(token);
		} catch (Exception e) {
			log.info("some error in auth microservice");
			throw new MicroserviceException(e.getMessage());
		}

		// check if token is valid
		if (response.getBody().isValid()) {

			// check if the employeeId does not matches with the logged user
			if (response.getBody().getEmpid() != employeeId) {
				successResponse.setMessage("user is invalid");
				successResponse.setStatus(HttpStatus.UNAUTHORIZED);
				successResponse.setTimestamp(new Date());
				return successResponse;
			}

			// check if the offer is present or not
			Optional<Offer> offer = offerRepository.findById(offerId);
			if (!offer.isPresent()) {
				throw new OfferNotFoundException("offer not found");
			}

			// if already closed
			if (offer.get().getClosedDate() != null) {
				successResponse.setMessage("offer is already closed");
				successResponse.setStatus(HttpStatus.BAD_REQUEST);
				successResponse.setTimestamp(new Date());
				return successResponse;
			}

			// if offer is present then check the initial stage of the offer
			else if (offer.get().getEngagedDate() != null) {
				throw new OfferAlreadyEngagedException("offer cannot be engaged");
			}

			// if the offer is available
			else {
				Employee employee;
				try {
					employee = employeeClient.getEmployee(token, employeeId);
				} catch (Exception e) {
					throw new MicroserviceException(e.getMessage());
				}

				// check if offer belongs to the same employee
				if (offer.get().getEmp().getId() == employeeId) {
					successResponse.setMessage("Employee cannot be engaged with his own offer");
					successResponse.setStatus(HttpStatus.BAD_REQUEST);
					successResponse.setTimestamp(new Date());
					return successResponse;
				}

				// else set the engaged employee id and the engagedDate
				Offer offer_real = offer.get();
				offer_real.setEngagedEmp(employee);
				offer_real.setEngagedDate(new Date());
				offerRepository.save(offer_real);

				// set the response
				successResponse.setMessage("engaged in the offer successfully");
				successResponse.setStatus(HttpStatus.CREATED);
				successResponse.setTimestamp(new Date());
				return successResponse;
			}
		} else {
			log.info("invalid or expired token");
			throw new InvalidTokenException("token is invalid");
		}
	}

	/**
	 * service to update an existing offer
	 * 
	 * @param offerDetails
	 * @return
	 * @throws OfferNotFoundException
	 * @throws MicroserviceException,InvalidTokenException
	 */
	public SuccessResponse editOffer(String token, Offer offerDetails)
			throws OfferNotFoundException, MicroserviceException, InvalidTokenException {

		ResponseEntity<AuthResponse> response;

		// authenticate the user
		try {
			response = authClient.verifyToken(token);
		} catch (Exception e) {
			log.info("some error in auth microservice");
			throw new MicroserviceException(e.getMessage());
		}

		// check if token is valid
		if (response.getBody().isValid()) {
			// get the offer from the repository
			Optional<Offer> offer = offerRepository.findById(offerDetails.getId());

			// if the offer is not found
			if (!offer.isPresent()) {
				throw new OfferNotFoundException("no offer found");
			}

			// check if the offer belongs to the user
			if (offer.get().getEmp().getId() != response.getBody().getEmpid()) {
				throw new InvalidTokenException("user cannot edit this offer");
			}

			// update the new details
			Offer offerReal = offer.get();
			offerReal.setCategory(offerDetails.getCategory());
			offerReal.setDescription(offerDetails.getDescription());
			offerReal.setName(offerDetails.getName());

			// save the details to offer repository
			offerRepository.save(offerReal);

			// prepare the response
			successResponse.setMessage("offer updated successfully");
			successResponse.setStatus(HttpStatus.OK);
			successResponse.setTimestamp(new Date());
			return successResponse;

		} else {
			log.info("invalid or expired token");
			throw new InvalidTokenException("token is invalid");
		}
	}

	/**
	 * service to add a new offers
	 * 
	 * @param offer
	 * @return
	 * @throws EmployeeNotFoundException
	 * @throws MicroserviceException,InvalidTokenException
	 */
	public SuccessResponse addOffer(String token, Offer offer)
			throws EmployeeNotFoundException, MicroserviceException, InvalidTokenException {

		ResponseEntity<AuthResponse> response;

		// authenticate the user
		try {
			response = authClient.verifyToken(token);
		} catch (Exception e) {
			log.info("some error in auth microservice");
			throw new MicroserviceException(e.getMessage());
		}

		// check if token is valid
		if (response.getBody().isValid()) {

			int empId = response.getBody().getEmpid(); // demo
			Employee emp;

			// retrieve the employee
			try {
				emp = employeeClient.getEmployee(token, empId);
			} catch (Exception e) {
				throw new MicroserviceException(e.getMessage());
			}

			offer.setEmp(emp);
			offer.setOpenDate(new Date());
			offer.setClosedDate(null);
			offer.setEngagedDate(null);

			offerRepository.save(offer);
			successResponse.setMessage("successfully added offer");
			successResponse.setStatus(HttpStatus.CREATED);
			successResponse.setTimestamp(new Date());
			return successResponse;
		} else {
			log.info("invalid or expired token");
			throw new InvalidTokenException("token is invalid");
		}
	}

	/**
	 * service to return offers by a particular emp_id
	 * 
	 * @param emp_id
	 * @return
	 * @throws OfferNotFoundException
	 * @throws MicroserviceException
	 */
	public List<Offer> getOffersById(String token, int emp_id)
			throws OfferNotFoundException, MicroserviceException, InvalidTokenException {
		ResponseEntity<AuthResponse> response;

		// authenticate the user
		try {
			response = authClient.verifyToken(token);
		} catch (Exception e) {
			log.info("some error in auth microservice");
			throw new MicroserviceException(e.getMessage());
		}

		// check if token is valid
		if (response.getBody().isValid()) {

			// verify the employee
			if (response.getBody().getEmpid() != emp_id) {
				throw new InvalidTokenException("token is invalid for the current user");
			}

			// get the offer details for the employee
			List<Offer> offers = offerRepository.getByEmpId(emp_id);

			if (offers.size() == 0) {
				throw new OfferNotFoundException("no offers found");
			}
			return offers;
		} else {
			log.info("expired or invalid token");
			throw new InvalidTokenException("token is invalid");
		}
	}

	/**
	 * retrieve points for a particular id
	 * 
	 * @param token
	 * @param emp_id
	 * @return
	 * @throws MicroserviceException
	 * @throws InvalidTokenException
	 */
	public int getPointsById(String token, int emp_id) throws MicroserviceException, InvalidTokenException {
		ResponseEntity<AuthResponse> response;

		// authenticate the user
		try {
			response = authClient.verifyToken(token);
		} catch (Exception e) {
			log.info("some error in auth microservice");
			throw new MicroserviceException(e.getMessage());
		}

		// check if token is valid
		if (response.getBody().isValid()) {

			// verify the user
			if (response.getBody().getEmpid() != emp_id) {
				throw new InvalidTokenException("token is invalid for user");
			}
			// return user points
			Employee emp;
			try {
				emp = employeeClient.getEmployee(token, emp_id);
			} catch (Exception e) {
				throw new MicroserviceException(e.getMessage());
			}

			return emp.getPointsGained();
		} else {
			throw new InvalidTokenException("token is invalid or expired");
		}
	}

	public SuccessResponse updateLikes(String token, int id) throws MicroserviceException, OfferNotFoundException {
		ResponseEntity<AuthResponse> response;
		// authenticate the user
		try {
			response = authClient.verifyToken(token);
		} catch (Exception e) {
			log.info("some error in auth microservice");
			throw new MicroserviceException(e.getMessage());
		}

		// check if token is valid
		if (response.getBody().isValid()) {
			Offer offer = offerRepository.findById(id).orElse(null);

			// check if the offer is available
			if (offer == null) {
				throw new OfferNotFoundException("offer not found");
			}

			// update the likes
			int likes = offer.getLikedByEmployees().size();
			log.info("" + offer);
			offer.setLikes(likes);

			// save in the repository
			offerRepository.save(offer);

			successResponse.setMessage("likes updated successfully");
			successResponse.setStatus(HttpStatus.OK);
			successResponse.setTimestamp(new Date());
			return successResponse;
		} else {
			throw new InvalidTokenException("invalid user");
		}
	}
}
