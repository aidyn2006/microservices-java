package org.example.authservice.controller;


import lombok.RequiredArgsConstructor;
import org.example.authservice.dto.request.PasswordChangeRequest;
import org.example.authservice.service.PasswordService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/password")
public class PasswordController {
    private final PasswordService passwordService;

    @PostMapping("/forgot-password")
    public String changePassword(@RequestBody PasswordChangeRequest request) {
        return passwordService.confirmPassword(request);
    }

    @PostMapping("/request-password-change")
    public String requestPasswordChange(@RequestParam String email) {
        return passwordService.changePassword(email);
    }
}
