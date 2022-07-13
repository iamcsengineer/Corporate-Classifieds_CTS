package com.cts.pointsmicroservice.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cts.pointsmicroservice.client.AuthClient;
import com.cts.pointsmicroservice.client.EmployeeClient;
import com.cts.pointsmicroservice.client.OfferClient;
import com.cts.pointsmicroservice.exception.InvalidUserException;
import com.cts.pointsmicroservice.exception.MicroserviceException;
import com.cts.pointsmicroservice.model.AuthResponse;
import com.cts.pointsmicroservice.model.MessageResponse;
import com.cts.pointsmicroservice.model.Offer;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PointsServiceImpl implements PointsService {

	@Autowired
	AuthClient authClient;

	@Autowired
	OfferClient offerClient;

	@Autowired
	MessageResponse messageResponse;

	@Autowired
	EmployeeClient employeeClient;

	// retrieve the points gained by an employee
	@Override
	public Integer getPoints(String token, int id) throws MicroserviceException, InvalidUserException {
		log.info("Inside getpoints");
		AuthResponse authResponse;
		// verify the token
		try {
			authResponse = authClient.verifyToken(token).getBody();
		} catch (Exception e) {
			throw new MicroserviceException(e.getMessage());
		}
		// validate the user
		if (authResponse.isValid()) {
			Integer points;
			// retrieve the points
			try {
				points = offerClient.getPointsById(token, id);
			} catch (Exception e) {
				throw new MicroserviceException(e.getMessage());
			}
			return points;
		} else {
			log.error("Token invalid");
			throw new InvalidUserException("Invalid User");
		}
	}

	// refresh the points for an employee
	@Override
	public MessageResponse refreshPoints(String token, int id) throws MicroserviceException, InvalidUserException {
		log.info("Inside refreshpoints");
		AuthResponse authResponse;
		Integer points = 0;
		List<Offer> offerList;

		// validate the token
		try {
			authResponse = authClient.verifyToken(token).getBody();
		} catch (Exception e) {
			throw new MicroserviceException(e.getMessage());
		}

		// validate the user
		if (authResponse.isValid()) {

			// verify the user
			if (authResponse.getEmpid() != id) {
				throw new InvalidUserException("token is not valid for the user");
			}

			// get the offers for the respective employee id
			try {
				offerList = offerClient.getOfferByEmpId(token, id);
			} catch (Exception e) {
				throw new MicroserviceException(e.getMessage());
			}

			// conditions
			for (Offer offer : offerList) {

				// if an offer gets more than 100 likes in less than 2 days
				if (new Date().compareTo(offer.getOpenDate()) <= 2) {
					if (offer.getLikes() > 100) {
						points += 50;
					}

					// if the offer gets more than 50 likes but less than 100
					else if (offer.getLikes() > 50) {
						points += 10;
					}
				}

				// if offer is engaged within 2 days
				if (offer.getEngagedDate() != null && offer.getEngagedDate().compareTo(offer.getOpenDate()) <= 2) {
					points += 100;
				}
			}

			// save the new points for the employee
			try {
				ResponseEntity<MessageResponse> response = employeeClient.savePoints(token, points);
				log.info(response.toString());
			} catch (Exception e) {
				throw new MicroserviceException(e.getMessage());
			}
			messageResponse.setMessage("points refreshed successfully " + points);
			messageResponse.setStatus(HttpStatus.OK);
			messageResponse.setTimeStamp(new Date());

			return messageResponse;
		} else {
			log.error("Token invalid");
			throw new InvalidUserException("Invalid User");
		}
	}

}
