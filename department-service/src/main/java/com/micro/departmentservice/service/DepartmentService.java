package com.micro.departmentservice.service;

import com.micro.departmentservice.dto.DepartmentDto;

public interface DepartmentService {
	
	DepartmentDto addDepartment(DepartmentDto departmentDto);
	DepartmentDto getDepartmentByCode(String code);

}
