package com.cts.employeemicroservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EmployeeTest {

	Employee emp=new Employee();
	
	@Test
	void testEmpId()
	{
		emp.setId(1);
		assertEquals(emp.getId(),1);
	}
	
	@Test
	void testEmpName()
	{
		emp.setName("abc");
		assertEquals(emp.getName(),"abc");
	}
	
	@Test
	void testEmpDepartment()
	{
		emp.setDepartment("Full Stack");
		assertEquals(emp.getDepartment(),"Full Stack");
	}
	
	@Test
	void testEmpGender()
	{
		emp.setGender("Male");
		assertEquals(emp.getGender(),"Male");
	}
	
	@Test
	void testEmpAge()
	{
		emp.setAge(23);
		assertEquals(emp.getAge(),23);
	}
	
	@Test
	void testEmpContactNumber()
	{
		long number = Long.parseLong(new String("959983990"));
		emp.setContactNumber(number);
		assertEquals(emp.getContactNumber(),959983990);
	}
	
	@Test
	void testEmpEmail()
	{
		emp.setEmail("abc@gmail.com");
		assertEquals(emp.getEmail(),"abc@gmail.com");
	}
	
	@Test
	void testEmpPoints()
	{
		emp.setPointsGained(100);
		assertEquals(emp.getPointsGained(),100);
	}
	
	@Test
	void testToString() {
		String string = emp.toString();
		assertEquals(emp.toString(), string);
	}
}