package com.example.Employee_Details.DTO;

import com.example.Employee_Details.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
@AllArgsConstructor
public class EmployeeResponse {
    private ArrayList<Employee> employees = new ArrayList<>();
}
