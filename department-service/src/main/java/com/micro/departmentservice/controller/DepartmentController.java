package com.micro.departmentservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.departmentservice.dto.DepartmentDto;
import com.micro.departmentservice.serviceimpl.DepartmentServiceImpl;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/department")
public class DepartmentController {
	
	private DepartmentServiceImpl departmentServiceImpl;
	
	@PostMapping("/addDepartment")
	public ResponseEntity<?> addDepartment(@RequestBody DepartmentDto departmentDto) {
		DepartmentDto savedDepartmentDto = departmentServiceImpl.addDepartment(departmentDto);
		return new ResponseEntity<>(savedDepartmentDto, HttpStatus.CREATED);
	}
	
	@GetMapping("/getDepartmentByCode/{code}")
	public ResponseEntity<?> getDepartmentByCode(@PathVariable("code") String departmentCode) {
		DepartmentDto savedDepartmentDto = departmentServiceImpl.getDepartmentByCode(departmentCode);
		return new ResponseEntity<>(savedDepartmentDto, HttpStatus.OK);
	}

}
