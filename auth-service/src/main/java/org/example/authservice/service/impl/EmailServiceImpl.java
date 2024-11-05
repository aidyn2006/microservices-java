package org.example.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.authservice.service.EmailService;
import org.example.authservice.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl {

    private final JavaMailSender mailSender;
    private final UserService userService;

    @Value("${email.from}")
    private String emailFrom;

    public String generateVerificationCode() {
        Random random = new Random();
        int code = random.nextInt(89999) + 10000;
        return String.valueOf(code);
    }

    public void sendVerificationEmail(String email, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailFrom);
        message.setTo(email);
        message.setSubject("Подтверждение регистрации");
        message.setText("Ваш код подтверждения: " + code);
        mailSender.send(message);
    }

    public void sendMessageToEmail() throws Exception {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailFrom);
        message.setTo(userService.getEmailByUserId());
        message.setSubject("Новая книга");
        message.setText("Хей привет! Наш читатель, новые книги уже на сайте!");
        mailSender.send(message);
    }

}
