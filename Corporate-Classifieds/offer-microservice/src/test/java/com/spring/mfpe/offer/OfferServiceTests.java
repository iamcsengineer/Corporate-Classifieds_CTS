package com.spring.mfpe.offer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
import com.spring.mfpe.offer.repositories.OfferRepository;
import com.spring.mfpe.offer.services.OfferService;

@SpringBootTest
public class OfferServiceTests {

	@InjectMocks
	OfferService offerService;

	@Mock
	AuthClient authClient;

	@Mock
	EmployeeClient empClient;

	@Mock
	OfferRepository offerRepository;

	public static AuthResponse authResponse;
	public static SuccessResponse successResponse;

	static {
		authResponse = new AuthResponse();
		authResponse.setEmpid(1);
		authResponse.setUsername("prateek");
		authResponse.setValid(true);

		SuccessResponse successResponse = new SuccessResponse();
		successResponse.setMessage("engaged in the offer successfully");
		successResponse.setStatus(HttpStatus.CREATED);
		successResponse.setTimestamp(new Date());

	}

	@Test
	public void getOfferDetailsTest() throws InvalidTokenException, OfferNotFoundException, MicroserviceException {

		ResponseEntity<AuthResponse> response = new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);
		Optional<Offer> offer = Optional.ofNullable(new Offer());

		when(authClient.verifyToken("token")).thenReturn(response);
		when(offerRepository.findById(1)).thenReturn(offer);

		Offer resultOffer = offerService.getOfferDetails("token", 1);
		assertEquals(resultOffer, offer.get());

	}

	@Test
	public void getOfferByCategoryTest() throws InvalidTokenException, OfferNotFoundException, MicroserviceException {
		ResponseEntity<AuthResponse> response = new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);
		List<Offer> offers = new ArrayList<Offer>();
		offers.add(new Offer());

		when(authClient.verifyToken("token")).thenReturn(response);
		when(offerRepository.findByCategory("electronics")).thenReturn(offers);

		List<Offer> resultOffers = offerService.getOfferByCategory("token", "electronics");
		assertEquals(resultOffers, offers);
	}

	@Test
	public void getOfferByTopLikesTest() throws InvalidTokenException, OfferNotFoundException, MicroserviceException {
		ResponseEntity<AuthResponse> response = new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);
		List<Offer> offers = new ArrayList<Offer>();
		offers.add(new Offer());

		when(authClient.verifyToken("token")).thenReturn(response);
		when(offerRepository.findAll(PageRequest.of(0, 3, Sort.by("likes").descending()))).thenReturn(offers);

		List<Offer> resultOffers = offerService.getOfferByTopLikes("token");

		assertEquals(offers, resultOffers);
	}

	@Test
	public void getOfferByPostedDateTest()
			throws InvalidTokenException, OfferNotFoundException, MicroserviceException, ImproperDateException {
		ResponseEntity<AuthResponse> response = new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);

		List<Offer> offers = new ArrayList<Offer>();
		offers.add(new Offer());

		String date = "2021-07-23";
		LocalDate currentDate = LocalDate.parse(date);
		int month = currentDate.getMonthValue();
		int year = currentDate.getYear();
		int day = currentDate.getDayOfMonth();

		when(authClient.verifyToken("token")).thenReturn(response);
		when(offerRepository.getByPostedDate(month, year, day)).thenReturn(offers);

		List<Offer> resultOffers = offerService.getOfferByPostedDate("token", date);

		assertEquals(offers, resultOffers);
	}

	@Test
	public void getOffersByIdTest() throws InvalidTokenException, OfferNotFoundException, MicroserviceException {
		ResponseEntity<AuthResponse> response = new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);

		List<Offer> offers = new ArrayList<Offer>();
		offers.add(new Offer());

		when(authClient.verifyToken("token")).thenReturn(response);
		when(offerRepository.getByEmpId(1)).thenReturn(offers);

		List<Offer> resultOffers = offerService.getOffersById("token", 1);
		assertEquals(offers, resultOffers);
	}
	
	@Test
	public void getPointsByIdTest() throws InvalidTokenException, OfferNotFoundException, MicroserviceException {
		ResponseEntity<AuthResponse> response = new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);

		List<Offer> offers = new ArrayList<Offer>();
		offers.add(new Offer());

		Employee emp = new Employee();
		when(authClient.verifyToken("token")).thenReturn(response);
		when(empClient.getEmployee("token",1)).thenReturn(emp);

		int points = offerService.getPointsById("token", 1);
		assertEquals(emp.getPointsGained(), points);
	}
}
