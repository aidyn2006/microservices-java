package org.example.authservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.authservice.dto.request.UserDtoLog;
import org.example.authservice.dto.request.UserDtoReg;
import org.example.authservice.dto.response.AuthResponse;
import org.example.authservice.service.AuthService;
import org.example.authservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

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
    @GetMapping("/get-user-id")
    public ResponseEntity<Long> getUserId() throws Exception {
        Long userId = userService.getUserIdFromContext();
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(userId);
    }
}
