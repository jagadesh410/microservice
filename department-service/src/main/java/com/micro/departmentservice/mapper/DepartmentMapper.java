package com.micro.departmentservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.micro.departmentservice.dto.DepartmentDto;
import com.micro.departmentservice.entity.Department;

@Mapper
public interface DepartmentMapper {
	
	DepartmentMapper MAPPER = Mappers.getMapper(DepartmentMapper.class);

	DepartmentDto mapToDepartmentDto(Department department);
	Department mapToDepartment(DepartmentDto departmentDto);
	
}
