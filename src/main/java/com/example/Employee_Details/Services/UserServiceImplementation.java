package com.example.Employee_Details.Services;

import com.example.Employee_Details.DTO.UserRequest;
import com.example.Employee_Details.DTO.UserResponse;
import com.example.Employee_Details.DTO.UserResponseDTO;
import com.example.Employee_Details.jwt.JwtUtils;
import com.example.Employee_Details.model.User;
import com.example.Employee_Details.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {

    @Value("${success.user.created}")
    private String userCreatedMessage;

    private static final Logger logger = LogManager.getLogger(UserServiceImplementation.class);

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<UserResponseDTO> registerUser(UserRequest userRequest) {
        // Check if the user already exists
//        if (userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
//            return new ResponseEntity<>(new UserResponseDTO(-1, 409, "Email already Present", null), HttpStatusCode.valueOf(409));
//        }

        User newUser = new User();
        newUser.setEmail(userRequest.getEmail());
        newUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        newUser.setRole(userRequest.getRole());

        logger.info(userCreatedMessage);
        userRepository.save(newUser);

        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .builder()
                .username(newUser.getEmail())
                .password(newUser.getPassword())
                .roles(newUser.getRole().name())
                .build();

        String jwtToken = jwtUtils.generateTokenFromUsername(userDetails);

        UserResponse response = new UserResponse(newUser.getEmail(), newUser.getRole(), jwtToken);

        return new ResponseEntity<>(new UserResponseDTO(0, 200, userCreatedMessage, response), HttpStatusCode.valueOf(200));
    }


}
