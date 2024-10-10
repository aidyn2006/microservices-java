package org.example.authservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.authservice.dto.request.UserDtoLog;
import org.example.authservice.dto.request.UserDtoReg;
import org.example.authservice.dto.response.AuthResponse;
import org.example.authservice.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserController {

    private final AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody UserDtoReg reg) throws Exception {
       return authService.registration(reg);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody UserDtoLog log) throws Exception {
        return authService.login(log);
    }
    @PostMapping("/confirm")
    public String confirmRegistration(@RequestParam String email, @RequestParam String verificationCode) throws Exception {
        return authService.confirmRegistration(email, verificationCode);
    }

    @PostMapping("/request-password-change")
    public String requestPasswordChange(@RequestParam String email) {
        return authService.changePassword(email);
    }

    @PostMapping("/forgot-password")
    public String changePassword(@RequestParam String email, @RequestParam String verificationCode, @RequestParam String newPassword) throws Exception {
        return authService.confirmPassword(email,verificationCode,newPassword);
    }


}
