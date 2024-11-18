package com.micro.employeeservice.serviceimpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.micro.employeeservice.client.APIClient;
import com.micro.employeeservice.dto.APIResponseDto;
import com.micro.employeeservice.dto.DepartmentDto;
import com.micro.employeeservice.dto.EmployeeDto;
import com.micro.employeeservice.entity.Employee;
import com.micro.employeeservice.exceptions.EmployeeNotFound;
import com.micro.employeeservice.mapper.EmployeeMapper;
import com.micro.employeeservice.repository.EmployeeRepository;
import com.micro.employeeservice.service.EmployeeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

//	private RestTemplate restTemplate;

//	private WebClient webClient;
	
	private APIClient apiClient;

	@Override
	public EmployeeDto addEmployee(EmployeeDto employeeDto) {
		// TODO Auto-generated method stub
		Employee employee = EmployeeMapper.MAPPER.mapToEmployee(employeeDto);
		Employee savedEmployee = employeeRepository.save(employee);
		return EmployeeMapper.MAPPER.mapToEmployeeDto(savedEmployee);
	}

	@Override
	public APIResponseDto getEmployeeById(Long employeeId) {
		// TODO Auto-generated method stub
		Employee employee = employeeRepository.findById(employeeId).orElse(null);
		EmployeeDto employeeDto;
		if (employee != null) {
			employeeDto = EmployeeMapper.MAPPER.mapToEmployeeDto(employee);
		} else {
			throw new EmployeeNotFound("Employee not exists with ID : " + employeeId);
		}

//		Using RestTemplate -> 
//		ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8081/api/department/getDepartmentByCode/"+employee.getDepartmentCode(),
//				DepartmentDto.class);

//		DepartmentDto departmentDto = responseEntity.getBody();

//		Using WebClient -> 
//		DepartmentDto departmentDto = webClient.get().uri("http://localhost:8081/api/department/getDepartmentByCode/" + employee.getDepartmentCode())
//				.retrieve().bodyToMono(DepartmentDto.class).block();
		
//		Using openFeign
		DepartmentDto departmentDto = apiClient.getDepartmentByCode(employee.getDepartmentCode());

		APIResponseDto apiResponse = new APIResponseDto();
		apiResponse.setEmployeeDto(EmployeeMapper.MAPPER.mapToEmployeeDto(employee));
		apiResponse.setDepartmentDto(departmentDto);

		return apiResponse;
	}

}
