package com.micro.employeeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micro.employeeservice.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
