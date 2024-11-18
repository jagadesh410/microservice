package com.micro.employeeservice.exceptions;

public class EmployeeNotFound extends RuntimeException {
	
	public EmployeeNotFound(String message) {
		super(message);
	}

}
