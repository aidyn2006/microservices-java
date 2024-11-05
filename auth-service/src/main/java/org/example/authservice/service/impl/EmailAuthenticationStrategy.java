package org.example.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.authservice.dto.request.UserDtoLog;
import org.example.authservice.dto.response.AuthResponse;
import org.example.authservice.entity.User;
import org.example.authservice.exception.handling.IncorrectDataException;
import org.example.authservice.exception.handling.UserNotFoundException;
import org.example.authservice.repository.UserRepository;
import org.example.authservice.security.jwt.JwtService;
import org.example.authservice.service.AuthenticationStrategy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmailAuthenticationStrategy implements AuthenticationStrategy {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    @Override
    public AuthResponse authenticate(UserDtoLog log) {
        User user = userRepository.findByEmail(log.getEmail())
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден!"));

        if (passwordEncoder.matches(log.getPassword(), user.getPassword())) {
            var jwtToken = jwtService.generateToken(user.getUsername());
            return AuthResponse.builder().accessToken(jwtToken).build();
        } else {
            throw new IncorrectDataException("Неверные данные!");
        }
    }
}
