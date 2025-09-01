package com.myproject.ridecabapp.dto;

import lombok.Data;
// will be used for logging in a user
@Data
public class LoginRequestDto {
    private String email;
    private String password;
}
