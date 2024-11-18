package com.micro.departmentservice.exception;

public class DepartmentNotFound extends RuntimeException {
	
	public DepartmentNotFound(String message) {
		super(message);
	}

}
