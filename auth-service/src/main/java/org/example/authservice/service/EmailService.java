package org.example.authservice.service;

public interface EmailService {
    String generateVerificationCode();
    void sendVerificationEmail(String email, String code);
    void sendMessageToEmail() throws Exception;
}
