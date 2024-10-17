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
    public ResponseEntity<String> register(@RequestBody UserDtoReg reg) {
        String message = authService.registration(reg);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody UserDtoLog log) {
        AuthResponse response = authService.login(log);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/confirm")
    public ResponseEntity<String> confirmRegistration(@RequestParam String email,
                                                      @RequestParam String verificationCode) {
        String message = authService.confirmRegistration(email, verificationCode);
        return ResponseEntity.ok(message);
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
