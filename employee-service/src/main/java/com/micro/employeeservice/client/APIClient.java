package com.micro.employeeservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.micro.employeeservice.dto.DepartmentDto;

@FeignClient(name = "DEPARTMENT-SERVICE")
public interface APIClient {

	@GetMapping("/api/department/getDepartmentByCode/{code}")
	DepartmentDto getDepartmentByCode(@PathVariable("code") String departmentCode);
	
}
