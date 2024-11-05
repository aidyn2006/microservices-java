package org.example.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.authservice.dto.request.UserDtoReg;
import org.example.authservice.entity.User;
import org.example.authservice.service.UserFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class DefaultUserFactory implements UserFactory {
    private final PasswordEncoder passwordEncoder;


    @Override
    public User createUser(UserDtoReg register) {
        return User.builder()
                .username(register.getUsername())
                .phoneNumber(register.getPhoneNumber())
                .createdAt(LocalDateTime.now())
                .password(passwordEncoder.encode(register.getPassword()))
                .role(register.getRole())
                .email(register.getEmail())
                .build();
    }
}
