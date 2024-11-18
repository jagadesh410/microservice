package com.micro.employeeservice.service;

import com.micro.employeeservice.dto.APIResponseDto;
import com.micro.employeeservice.dto.EmployeeDto;

public interface EmployeeService {
	
	EmployeeDto addEmployee(EmployeeDto employeeDto);
	APIResponseDto getEmployeeById(Long employeeId);

}
