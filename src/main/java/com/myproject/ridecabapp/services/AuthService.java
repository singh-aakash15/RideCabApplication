package com.myproject.ridecabapp.services;

import com.myproject.ridecabapp.dto.DriverDto;
import com.myproject.ridecabapp.dto.SignupDto;
import com.myproject.ridecabapp.dto.UserDto;

public interface AuthService {

    String login(String email, String password);

    UserDto signup(SignupDto signupDto);

    DriverDto onboardNewDriver(Long userId);
}
