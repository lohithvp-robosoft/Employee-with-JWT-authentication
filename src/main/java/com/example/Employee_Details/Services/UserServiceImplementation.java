package com.example.Employee_Details.Services;

import com.example.Employee_Details.DTO.UserRequest;
import com.example.Employee_Details.DTO.UserResponse;
import com.example.Employee_Details.DTO.UserResponseDTO;
import com.example.Employee_Details.model.User;
import com.example.Employee_Details.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImplementation.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<UserResponseDTO> registerUser(UserRequest userRequest) {
        if (userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
            return new ResponseEntity<>(new UserResponseDTO(-1, 409, "Email already Present", null), HttpStatusCode.valueOf(409));
        }
        User newUser = new User();
        newUser.setEmail(userRequest.getEmail());
        newUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        logger.info("User created successfully");
        logger.error("Its error");
        logger.debug("Its debug");
        userRepository.save(newUser);


        return new ResponseEntity<>(new UserResponseDTO(0, 200, "User successfully created", new UserResponse(newUser)), HttpStatusCode.valueOf(200));
    }
}
