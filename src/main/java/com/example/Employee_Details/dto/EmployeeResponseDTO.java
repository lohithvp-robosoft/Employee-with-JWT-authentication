package com.example.Employee_Details.dto;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

//@Builder
//@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponseDTO  implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long id;
    private int status;
    private int code;
    private String message;
    private EmployeeResponse data;

    public EmployeeResponseDTO(int status, int code, String message, EmployeeResponse employeeResponse) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = employeeResponse;
    }
}


