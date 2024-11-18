package com.micro.employeeservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.employeeservice.dto.APIResponseDto;
import com.micro.employeeservice.dto.EmployeeDto;
import com.micro.employeeservice.serviceimpl.EmployeeServiceImpl;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/employee")
@AllArgsConstructor
public class EmployeeController {
	
	private EmployeeServiceImpl employeeServiceImpl;
	
	@PostMapping("/addEmployee")
	public ResponseEntity<?> addEmployee(@RequestBody EmployeeDto employeeDto) {
		EmployeeDto savedEmployee = employeeServiceImpl.addEmployee(employeeDto);
		return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
	}
	
	@GetMapping("/getEmployeeById/{id}")
	public ResponseEntity<?> getEmployeeById(@PathVariable("id") Long employeeId) {
		APIResponseDto responseDto = employeeServiceImpl.getEmployeeById(employeeId);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}

}
