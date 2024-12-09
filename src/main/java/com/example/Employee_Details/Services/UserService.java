package com.example.Employee_Details.Services;

import com.example.Employee_Details.DTO.UserRequest;
import com.example.Employee_Details.DTO.UserResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    ResponseEntity<UserResponseDTO> registerUser(UserRequest userRequest);
}
