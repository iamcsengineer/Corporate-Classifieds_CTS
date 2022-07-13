package com.cts.employeemicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.employeemicroservice.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Integer>{

	
}
