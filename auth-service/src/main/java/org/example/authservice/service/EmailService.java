package org.example.authservice.service;

import org.springframework.stereotype.Service;

public interface EmailService {
    String generateVerificationCode();
    void sendVerificationEmail(String email, String code);
    void sendMessageToEmail() throws Exception;
}
