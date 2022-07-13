package com.cts.employeemicroservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;

public class EmployeeOffersTest {

	EmployeeOffers offers=new EmployeeOffers();
	EmployeeOffers empOffers=new EmployeeOffers();
	
	@Test
	void testOfferId()
	{
		offers.setId(1);
		assertEquals(offers.getId(),1);
	}
	
	@Test
	void testOfferName()
	{
		offers.setName("abc");
		assertEquals(offers.getName(),"abc");
	}
	
	@Test
	void testOfferDescription()
	{
		offers.setDescription("Apartment for rent");
		assertEquals(offers.getDescription(),"Apartment for rent");
	}
	
	@Test
	void testOfferEmpId()
	{
		offers.setId(1);
		assertEquals(offers.getId(),1);
	}
	
	@Test
	void testOfferEngagedEmpId()
	{
		Employee emp = new Employee();
		offers.setEngagedEmp(emp);
		assertEquals(offers.getEngagedEmp(),emp);
	}
	
	@Test
	void testOpenedDate()
	{
		offers.setOpenDate(new Date());
		assertEquals(offers.getOpenDate(),new Date());
	}
	
	@Test
	void testEngagedDate()
	{
		Date date = new Date();
		offers.setEngagedDate(date);
		assertEquals(offers.getEngagedDate(),date);
	}
	
	@Test
	void testClosedDate()
	{
		
		offers.setClosedDate(new Date());
		assertEquals(offers.getClosedDate(),new Date());
	}
	
	@Test
	void testLikes()
	{
		offers.setLikes(10);
		assertEquals(offers.getLikes(),10);
	}
	
	@Test
	void testOfferCategory()
	{
		offers.setCategory("Apartment Rental");
		assertEquals(offers.getCategory(),"Apartment Rental");
	}
	
	@Test
	void testToString() {
		String string = empOffers.toString();
		assertEquals(empOffers.toString(), string);
	}
}
