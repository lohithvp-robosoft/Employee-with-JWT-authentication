package com.example.Employee_Details.controller;

import com.example.Employee_Details.DTO.UserRequest;
import com.example.Employee_Details.DTO.UserResponseDTO;
import com.example.Employee_Details.Services.UserService;
import com.example.Employee_Details.jwt.JwtUtils;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Log4j2
public class UserController {
    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/v1/register")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRequest userRequest) {
        return userService.registerUser(userRequest);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public String userEndpoint() {
        return "Hello, User!";
    }

        @PreAuthorize("hasRole('ADMIN')")
//    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin")
    public String adminEndpoint() {

        //
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //
        logger.debug("Authenticated user roles: {}", authentication.getAuthorities());
        return "Hello, Admin!";

    }

}
