
package com.cts.employeemicroservice.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmployeeControllerTest {

	EmployeeController employeeController = new EmployeeController();

    @Test
    @DisplayName("Checking for EmployeeController - if it is loading or not for @GetMapping")
    void employeeControllerNotNullTest(){
        assertThat(employeeController).isNotNull();
    }
	
}