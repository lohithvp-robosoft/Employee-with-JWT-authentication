package com.example.Employee_Details.exception;

import com.example.Employee_Details.dto.EmployeeResponseDTO;
import com.example.Employee_Details.dto.UserResponseDTO;
import com.example.Employee_Details.Utils.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<EmployeeResponseDTO> handleNotFoundException(NotFoundException exception) {
        return ResponseUtil.errorResponseDefault(exception.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {EmptyEmployeeListException.class})
    public ResponseEntity<EmployeeResponseDTO> handleEmptyEmployeeListException(EmptyEmployeeListException exception) {
        return ResponseUtil.errorResponseDefault(exception.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleGenericException(Exception ex) {
        return new ResponseEntity<>(new UserResponseDTO<>(-1, 500, "Internal Server Error: " + ex.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {AuthenticationCredentialsNotFoundException.class, AuthenticationException.class})
    public ResponseEntity<Object> handleAuthenticationException(Exception exception) {
        return new ResponseEntity<>(
                new UserResponseDTO<>(-1, HttpStatus.UNAUTHORIZED.value(), "Authentication Failed: " + exception.getMessage(), null),
                HttpStatus.UNAUTHORIZED
        );
    }
}
