package com.example.Employee_Details.controller;

import com.example.Employee_Details.DTO.EmployeeRequestDTO;
import com.example.Employee_Details.DTO.EmployeeResponseDTO;
import com.example.Employee_Details.Exception.EmptyEmployeeListException;
import com.example.Employee_Details.Exception.NotFoundException;
import com.example.Employee_Details.Services.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeServices employeeServices;

    @GetMapping("/v1/getAllEmployee")
    public ResponseEntity<EmployeeResponseDTO> getAllEmployeeData() throws EmptyEmployeeListException {
        return employeeServices.getAllEmployeeDetail();
    }

    @GetMapping("/v1/get-one-employee")
    public ResponseEntity<EmployeeResponseDTO> getOneEmployeeById(@RequestHeader("id") Long id) throws NotFoundException {
        return employeeServices.getEmployeeById(id);
    }

    @PostMapping("/v1/create-employee")
    public ResponseEntity<EmployeeResponseDTO> createEmployee(@RequestBody EmployeeRequestDTO employeeRequestDTO) {
        return employeeServices.addEmployee(employeeRequestDTO);
    }

    @PutMapping("/v1/update-employee")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(@RequestBody EmployeeRequestDTO updatedEmployee, @RequestHeader("id") Long id) throws NotFoundException {
        return employeeServices.updateEmployee(updatedEmployee, id);
    }

    @DeleteMapping("/v1/delete-employee")
    public ResponseEntity<EmployeeResponseDTO> deleteEmployee(@RequestParam("id") Long id) throws NotFoundException {
        return employeeServices.deleteEmployee(id);
    }

    @GetMapping("/v1/get-first-three-employee")
    public ResponseEntity<EmployeeResponseDTO> getFirstThreeEmployee() throws EmptyEmployeeListException {
        return employeeServices.getFirstThreeEmployee();
    }
}
