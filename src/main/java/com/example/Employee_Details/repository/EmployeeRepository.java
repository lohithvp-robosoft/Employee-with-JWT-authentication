package com.example.Employee_Details.repository;

import com.example.Employee_Details.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
