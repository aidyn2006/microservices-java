package org.example.authservice.service;

public interface PasswordService {
    String changePassword(String email);
    String confirmPassword(String email, String verificationCode, String newPassword);
}
