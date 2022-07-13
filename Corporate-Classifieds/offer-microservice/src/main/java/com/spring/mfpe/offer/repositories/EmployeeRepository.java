package com.spring.mfpe.offer.repositories;

import org.springframework.data.repository.CrudRepository;

import com.spring.mfpe.offer.entities.Employee;

//to access employee database
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

}
