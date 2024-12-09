package com.example.Employee_Details.Services;

import com.example.Employee_Details.DTO.EmployeeRequestDTO;
import com.example.Employee_Details.DTO.EmployeeResponseDTO;
import com.example.Employee_Details.Exception.EmptyEmployeeListException;
import com.example.Employee_Details.Exception.NotFoundException;
import com.example.Employee_Details.Utils.ResponseUtil;
import com.example.Employee_Details.model.Employee;
import com.example.Employee_Details.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServicesImpl implements EmployeeServices {

    @Autowired
    EmployeeRepository employeeRepository;


    @Value("${error.employee.notFound}")
    private String employeeNotFoundMessage;

    @Value("${error.employee.empty}")
    private String employeeListEmptyMessage;

    @Value("${success.employee.added}")
    private String employeeAddedMessage;

    @Value("${success.employee.deleted}")
    private String employeeDeletedMessage;

    @Override
    public ResponseEntity<EmployeeResponseDTO> getAllEmployeeDetail() throws EmptyEmployeeListException {

        ArrayList<Employee> employeeList = new ArrayList<>(employeeRepository.findAll());

        if (employeeList.isEmpty()) {
            throw new EmptyEmployeeListException(employeeListEmptyMessage);
        }
        return ResponseUtil.successResponse(employeeList);
    }

    @Override
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(Long id) throws NotFoundException {
        return employeeRepository.findById(id)
                .map(employee -> {
                    ArrayList<Employee> employeeList = new ArrayList<>();
                    employeeList.add(employee);
                    return ResponseUtil.successResponse(employeeList);
                })
                .orElseThrow(() -> new NotFoundException(String.format(employeeNotFoundMessage, id)));
    }

    @Override
    public ResponseEntity<EmployeeResponseDTO> addEmployee(EmployeeRequestDTO employeeRequestDTO) {
        Employee newEmployee = new Employee(employeeRequestDTO);
        ArrayList<Employee> newEmployeeList = new ArrayList<>();
        newEmployeeList.add(newEmployee);
        employeeRepository.save(newEmployee);
        return ResponseUtil.createdResponse(newEmployeeList, String.format(employeeAddedMessage, newEmployee.getName()));
    }

    @Override
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(EmployeeRequestDTO updatedEmployee, Long id) throws NotFoundException {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setName(updatedEmployee.getName());
                    employee.setDepartment(updatedEmployee.getDepartment());
                    employee.setDesignation(updatedEmployee.getDesignation());
                    employeeRepository.save(employee);
                    ArrayList<Employee> employeeList = new ArrayList<>();
                    employeeList.add(employee);
                    return ResponseUtil.successResponse(employeeList);
                })
                .orElseThrow(() -> new NotFoundException(String.format(employeeNotFoundMessage, id))
                );
    }

    @Override
    public ResponseEntity<EmployeeResponseDTO> deleteEmployee(Long id) throws NotFoundException {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            ArrayList<Employee> deletedEmployeeList = new ArrayList<>();
            deletedEmployeeList.add(employee.get());
            employeeRepository.delete(employee.get());
            return ResponseUtil.createdResponse(deletedEmployeeList, employeeDeletedMessage);
        } else {
            throw new NotFoundException(String.format(employeeNotFoundMessage, id));
        }
    }

    @Override
    public ResponseEntity<EmployeeResponseDTO> getFirstThreeEmployee() throws EmptyEmployeeListException {
        List<Employee> employeeList = employeeRepository.findAll();
        ArrayList<Employee> employeeResponseDTOList = new ArrayList<>();
        for (int i = 0; i < 3 && i < employeeList.size(); i++) {
            employeeResponseDTOList.add(employeeList.get(i));
        }
        if (employeeList.isEmpty()) {
            throw new EmptyEmployeeListException(employeeListEmptyMessage);
        }
        return ResponseUtil.successResponse(employeeResponseDTOList);
    }
}

