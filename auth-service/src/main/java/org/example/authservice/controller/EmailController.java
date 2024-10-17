package org.example.authservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.authservice.service.EmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/email")
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/send-message")
    public String sendMessage() throws Exception {
        emailService.sendMessageToEmail();
        return "Message sended";
    }

}
