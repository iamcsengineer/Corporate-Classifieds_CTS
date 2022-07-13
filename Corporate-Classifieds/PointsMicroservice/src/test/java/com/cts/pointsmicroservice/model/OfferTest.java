//package com.cts.pointsmicroservice.model;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import java.time.LocalDate;
//
//import org.junit.jupiter.api.Test;
//
//public class OfferTest {
//	
//	Offer offer = new Offer(1,"name",LocalDate.now(),LocalDate.now(),1,5);
//	
//	Offer offerOne = new Offer();
//	
//	@Test
//	public void offerIdTest()
//	{
//		offerOne.setOfferId(1);
//		assertEquals(offerOne.getOfferId(), 1);
//	}
//	
//	@Test
//	public void offerNameTest()
//	{
//		offerOne.setOfferName("name");
//		assertEquals(offerOne.getOfferName(), "name");
//	}
//	
//	@Test
//	public void openDateTest()
//	{
//		offerOne.setOpenedDate(LocalDate.now());
//		assertEquals(offerOne.getOpenedDate(), LocalDate.now());
//	}
//	
//	@Test
//	public void closedDateTest()
//	{
//		offerOne.setClosedDate(LocalDate.now());
//		assertEquals(offerOne.getClosedDate(), LocalDate.now());
//	}
//	
//	@Test
//	public void likesTest()
//	{
//		offerOne.setLikes(1);
//		assertEquals(offerOne.getLikes(), 1);
//	}
//	
//	@Test
//	public void empIdTest()
//	{
//		offerOne.setEmpId(1);
//		assertEquals(offerOne.getEmpId(), 1);
//	}
//
//}
