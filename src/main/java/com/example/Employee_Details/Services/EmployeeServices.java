package com.example.Employee_Details.Services;

import com.example.Employee_Details.dto.EmployeeRequestDTO;
import com.example.Employee_Details.dto.EmployeeResponseDTO;
import com.example.Employee_Details.exception.EmptyEmployeeListException;
import com.example.Employee_Details.exception.NotFoundException;
import com.example.Employee_Details.model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeServices {

    List<Employee> getAllEmployeeDetail() throws EmptyEmployeeListException;

    ResponseEntity<EmployeeResponseDTO> getEmployeeById(Long id) throws NotFoundException;

    ResponseEntity<EmployeeResponseDTO> addEmployee(EmployeeRequestDTO employeeRequestDTO);

    ResponseEntity<EmployeeResponseDTO> updateEmployee(EmployeeRequestDTO employee, Long id) throws NotFoundException;

    ResponseEntity<EmployeeResponseDTO> deleteEmployee(Long id) throws NotFoundException;

    ResponseEntity<EmployeeResponseDTO> getFirstThreeEmployee() throws EmptyEmployeeListException;
}
