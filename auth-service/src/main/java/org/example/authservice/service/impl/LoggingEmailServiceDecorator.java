package org.example.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.authservice.service.EmailService;
import org.example.authservice.service.EmailServiceDecorator;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoggingEmailServiceDecorator implements EmailServiceDecorator {
    private final EmailServiceImpl emailService;

    @Override
    public void sendVerificationEmail(String email, String code) {
        System.out.println("Отправка верификационного email: " + email);
        emailService.sendVerificationEmail(email, code);
        System.out.println("Email успешно отправлен: " + email);
    }

    @Override
    public void sendMessageToEmail() throws Exception {
        System.out.println("Отправка сообщений в email: ");
        emailService.sendMessageToEmail();
        System.out.println("Сообщения успешно отправлен в email: ");

    }

    @Override
    public String generateVerificationCode() {
        return emailService.generateVerificationCode();
    }
}
