package com.example.Employee_Details;

import com.example.Employee_Details.model.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class EmployeeDetailsApplication {
    public static ArrayList<Employee> employeeList = new ArrayList<>();

    public static void main(String[] args) {

        SpringApplication.run(EmployeeDetailsApplication.class, args);
    }
}
