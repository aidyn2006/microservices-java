package org.example.authservice.service;

import org.example.authservice.dto.request.UserDtoLog;
import org.example.authservice.dto.request.UserDtoReg;
import org.example.authservice.dto.response.AuthResponse;

public interface AuthService {
    AuthResponse login(UserDtoLog userDtoLog);
    String registration(UserDtoReg register);
    String confirmRegistration(String email, String verificationCode);



}
