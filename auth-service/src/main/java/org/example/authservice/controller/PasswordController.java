package org.example.authservice.controller;


import lombok.RequiredArgsConstructor;
import org.example.authservice.service.PasswordService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/password")
public class PasswordController {
    private final PasswordService passwordService;

    @PostMapping("/forgot-password")
    public String changePassword(@RequestParam String email, @RequestParam String verificationCode, @RequestParam String newPassword) throws Exception {
        return passwordService.confirmPassword(email,verificationCode,newPassword);
    }
    @PostMapping("/request-password-change")
    public String requestPasswordChange(@RequestParam String email) {
        return passwordService.changePassword(email);
    }
}
