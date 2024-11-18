package com.micro.departmentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptions {
	
	@ExceptionHandler(DepartmentNotFound.class)
	public ResponseEntity<?> handleDepartmentNotFound(DepartmentNotFound departmentNotFound) {
		return new ResponseEntity<>(departmentNotFound.getMessage(), HttpStatus.NOT_FOUND);
	}

}
