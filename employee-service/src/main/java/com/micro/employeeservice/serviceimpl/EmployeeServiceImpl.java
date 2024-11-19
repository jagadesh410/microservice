package com.micro.employeeservice.serviceimpl;

import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

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

//	@CircuitBreaker(name = "EMPLOYEE-SERVICE", fallbackMethod = "getDefaultMethod")
	@Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultMethod")
	@Override
	public APIResponseDto getEmployeeById(Long employeeId) {
		// TODO Auto-generated method stub
		
		LOGGER.info("inside of getEmployeeById()");
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
	
	public APIResponseDto getDefaultMethod(Long employeeId, Exception exception) {
		
		LOGGER.info("inside of getDefaultMethod()");
		
		EmployeeDto employeeDto = new EmployeeDto(1L, "firstName", "lastName", "defaultName@gmail.com", "QWERTY");
		DepartmentDto departmentDto = new DepartmentDto(1L, "defaultName", "default description", "QWERTY");

		APIResponseDto apiResponse = new APIResponseDto();
		apiResponse.setEmployeeDto(employeeDto);
		apiResponse.setDepartmentDto(departmentDto);

		return apiResponse;
		
	}

}
