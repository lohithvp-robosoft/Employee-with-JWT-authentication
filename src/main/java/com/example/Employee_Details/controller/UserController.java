package com.example.Employee_Details.controller;

import com.example.Employee_Details.DTO.UserRequest;
import com.example.Employee_Details.DTO.UserResponseDTO;
import com.example.Employee_Details.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/v1/register")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRequest userRequest) {
        return userService.registerUser(userRequest);
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public String userEndpoint(){
        return "Hello, User!";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String adminEndpoint() {
        return "Hello, Admin!";

    }

}
