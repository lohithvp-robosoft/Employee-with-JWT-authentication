package com.example.Employee_Details.controller;

import com.example.Employee_Details.dto.EmployeeRequestDTO;
import com.example.Employee_Details.dto.EmployeeResponse;
import com.example.Employee_Details.dto.EmployeeResponseDTO;
import com.example.Employee_Details.exception.EmptyEmployeeListException;
import com.example.Employee_Details.exception.NotFoundException;
import com.example.Employee_Details.Services.EmployeeServices;
import com.example.Employee_Details.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeServices employeeServices;

@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
@GetMapping("/v1/getAllEmployee")
public ResponseEntity<EmployeeResponseDTO> getAllEmployeeResponse() throws EmptyEmployeeListException {
    List<Employee> employees = employeeServices.getAllEmployeeDetail();
    EmployeeResponse employeeResponse = new EmployeeResponse((ArrayList<Employee>) employees);
    EmployeeResponseDTO responseDTO = new EmployeeResponseDTO(0, 200, "Success", employeeResponse);
    return ResponseEntity.ok(responseDTO);
}

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/v1/get-one-employee")
    public ResponseEntity<EmployeeResponseDTO> getOneEmployeeById(@RequestHeader("id") Long id) throws NotFoundException {
        return employeeServices.getEmployeeById(id);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping("/v1/create-employee")
    public ResponseEntity<EmployeeResponseDTO> createEmployee(@RequestBody EmployeeRequestDTO employeeRequestDTO) {
        return employeeServices.addEmployee(employeeRequestDTO);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PutMapping("/v1/update-employee")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(@RequestBody EmployeeRequestDTO updatedEmployee, @RequestHeader("id") Long id) throws NotFoundException {
        return employeeServices.updateEmployee(updatedEmployee, id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/v1/delete-employee")
    public ResponseEntity<EmployeeResponseDTO> deleteEmployee(@RequestParam("id") Long id) throws NotFoundException {
        return employeeServices.deleteEmployee(id);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/v1/get-first-three-employee")
    public ResponseEntity<EmployeeResponseDTO> getFirstThreeEmployee() throws EmptyEmployeeListException {
        return employeeServices.getFirstThreeEmployee();
    }

    //    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
//    @GetMapping("/v1/getAllEmployee")
//    public ResponseEntity<EmployeeResponseDTO> getAllEmployeeData() throws EmptyEmployeeListException {
//        return employeeServices.getAllEmployeeDetail();
//    }
}
