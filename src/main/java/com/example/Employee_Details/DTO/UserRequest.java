package com.example.Employee_Details.DTO;

import com.example.Employee_Details.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserRequest {
    private String email;
    private String password;
    private Role role;
}
