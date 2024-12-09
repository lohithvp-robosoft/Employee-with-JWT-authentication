package com.example.Employee_Details.DTO;

import com.example.Employee_Details.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String email;
    private String password;

    public UserResponse(User user) {
        this.email = user.getEmail();
        this.id = user.getId();
        this.password = user.getPassword();
    }
}
