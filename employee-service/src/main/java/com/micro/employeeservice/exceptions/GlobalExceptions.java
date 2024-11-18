package com.micro.employeeservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptions {
	
	@ExceptionHandler(EmployeeNotFound.class)
	public ResponseEntity<?> handleEmployeeNotFound(EmployeeNotFound employee) {
		return new ResponseEntity<>(employee.getMessage(), HttpStatus.NOT_FOUND);
	}

}
