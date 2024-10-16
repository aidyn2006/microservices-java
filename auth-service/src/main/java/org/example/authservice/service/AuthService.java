package org.example.authservice.service;

import org.example.authservice.dto.request.UserDtoLog;
import org.example.authservice.dto.request.UserDtoReg;
import org.example.authservice.dto.response.AuthResponse;

public interface AuthService {
    AuthResponse login(UserDtoLog userDtoLog) throws Exception;
    String registration(UserDtoReg register) throws Exception;
    String confirmRegistration(String email, String verificationCode) throws Exception;
    String confirmPassword(String email, String verificationCode, String newPassword) throws Exception;
    String changePassword(String email);
    Long getUserIdFromContext();
    void sendMessageToEmail();


}
