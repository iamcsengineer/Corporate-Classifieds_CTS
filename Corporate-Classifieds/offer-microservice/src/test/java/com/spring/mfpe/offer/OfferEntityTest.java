package com.spring.mfpe.offer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.mfpe.offer.entities.Employee;
import com.spring.mfpe.offer.entities.Offer;

@SpringBootTest
public class OfferEntityTest {

	@InjectMocks
	Offer offer;
	
	@InjectMocks
	Employee employee;
	
	@Test
	public void testNameGetterSetter() {
		offer.setName("offer1");
		assertEquals(offer.getName(),"offer1");
	}
	
	@Test
	public void testDescriptionGetterSetter() {
		offer.setDescription("offer description");
		assertEquals(offer.getDescription(),"offer description");
	}
	
	@Test
	public void testCategoryGetterSetter() {
		offer.setCategory("offer category");
		assertEquals(offer.getCategory(),"offer category");
	}
	
	
	@Test
	public void testOpenDateGetterSetter() {
		Date date = new Date();
		offer.setOpenDate(date);
		assertEquals(offer.getOpenDate(),date);
	}
	
	@Test
	public void testClosedDateGetterSetter() {
		Date date = new Date();
		offer.setClosedDate(date);
		assertEquals(offer.getClosedDate(),date);
	}
	
	@Test
	public void testEngagedDateGetterSetter() {
		Date date = new Date();
		offer.setEngagedDate(date);
		assertEquals(offer.getEngagedDate(),date);
	}
	
	@Test
	public void testEmpGetterSetter() {
		offer.setEmp(employee);
		assertEquals(offer.getEmp(),employee);
	}
	
	@Test
	public void testEngagedEmpGetterSetter() {
		offer.setEngagedEmp(employee);
		assertEquals(offer.getEngagedEmp(),employee);
	}
	
	@Test
	public void testLikedByEmployeesGetterSetter() {
		Set<Employee> emps = new HashSet<Employee>();
		emps.add(employee);
		offer.setLikedByEmployees(emps);
		assertEquals(offer.getLikedByEmployees(),emps);
	}
	
}
