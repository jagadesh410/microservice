package com.micro.employeeservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.micro.employeeservice.dto.EmployeeDto;
import com.micro.employeeservice.entity.Employee;

@Mapper
public interface EmployeeMapper {
	
	EmployeeMapper MAPPER = Mappers.getMapper(EmployeeMapper.class);
	
	EmployeeDto mapToEmployeeDto(Employee employee);
	Employee mapToEmployee(EmployeeDto employeeDto);

}
