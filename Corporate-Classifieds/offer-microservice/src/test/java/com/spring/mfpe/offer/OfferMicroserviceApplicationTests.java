package com.spring.mfpe.offer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.mfpe.offer.entities.Employee;
import com.spring.mfpe.offer.entities.Offer;
import com.spring.mfpe.offer.repositories.EmployeeRepository;
import com.spring.mfpe.offer.repositories.OfferRepository;

@SpringBootTest
class OfferMicroserviceApplicationTests {

	@Autowired
	EmployeeRepository empRepo;
	
	@Autowired
	OfferRepository offerRepo;
	
	@Test
	void contextLoads() {
	}

	@Test
	void testEmployee() {
		Employee emp = empRepo.findById(1).get();
//		System.out.println("offers:"+emp.getOffers());
		System.out.println("liked offers:" + emp.getLikedOffers());
	}
	
	@Test
	void testOffer() {
		Offer offer = offerRepo.findById(4).get();
		System.out.println(offer.getEngagedEmp());
	}
}
