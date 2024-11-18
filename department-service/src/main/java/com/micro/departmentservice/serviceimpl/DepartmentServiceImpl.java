package com.micro.departmentservice.serviceimpl;

import org.springframework.stereotype.Service;

import com.micro.departmentservice.dto.DepartmentDto;
import com.micro.departmentservice.entity.Department;
import com.micro.departmentservice.exception.DepartmentNotFound;
import com.micro.departmentservice.mapper.DepartmentMapper;
import com.micro.departmentservice.repository.DepartmentRepository;
import com.micro.departmentservice.service.DepartmentService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
	
	private DepartmentRepository departmentRepository;

	@Override
	public DepartmentDto addDepartment(DepartmentDto departmentDto) {
		// TODO Auto-generated method stub
		Department department = DepartmentMapper.MAPPER.mapToDepartment(departmentDto);
		Department savedDepartment = departmentRepository.save(department);
		DepartmentDto savedDepartmentDto = DepartmentMapper.MAPPER.mapToDepartmentDto(savedDepartment);
		return savedDepartmentDto;
	}

	@Override
	public DepartmentDto getDepartmentByCode(String code) {
		// TODO Auto-generated method stub
		Department department = departmentRepository.findByDepartmentCode(code);
		if(department != null) {
			DepartmentDto departmentDto = DepartmentMapper.MAPPER.mapToDepartmentDto(department);
			return departmentDto;
		} else {
			throw new DepartmentNotFound("Department not found with code : "+code);
		}
	}

}
