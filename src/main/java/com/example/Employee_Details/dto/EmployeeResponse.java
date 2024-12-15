package com.example.Employee_Details.dto;

import com.example.Employee_Details.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

@Setter
@Getter
@AllArgsConstructor
public class EmployeeResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private ArrayList<Employee> employees = new ArrayList<>();
}
