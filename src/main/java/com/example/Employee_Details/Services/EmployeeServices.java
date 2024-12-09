package com.example.Employee_Details.Services;

import com.example.Employee_Details.DTO.EmployeeRequestDTO;
import com.example.Employee_Details.DTO.EmployeeResponseDTO;
import com.example.Employee_Details.Exception.EmptyEmployeeListException;
import com.example.Employee_Details.Exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeServices {

    ResponseEntity<EmployeeResponseDTO> getAllEmployeeDetail() throws EmptyEmployeeListException;

    ResponseEntity<EmployeeResponseDTO> getEmployeeById(Long id) throws NotFoundException;

    ResponseEntity<EmployeeResponseDTO> addEmployee(EmployeeRequestDTO employeeRequestDTO);

    ResponseEntity<EmployeeResponseDTO> updateEmployee(EmployeeRequestDTO employee, Long id) throws NotFoundException;

    ResponseEntity<EmployeeResponseDTO> deleteEmployee(Long id) throws NotFoundException;

    ResponseEntity<EmployeeResponseDTO> getFirstThreeEmployee() throws EmptyEmployeeListException;
}
