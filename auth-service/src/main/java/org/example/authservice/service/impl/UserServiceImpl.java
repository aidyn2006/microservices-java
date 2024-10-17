package org.example.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.authservice.entity.User;
import org.example.authservice.repository.UserRepository;
import org.example.authservice.service.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public String getEmailByUserId() throws Exception {
        return userRepository.findById(getUserIdFromContext())
                .map(User::getEmail)
                .orElseThrow(() -> new Exception("Пользователь не найден."));
    }
    public Long getUserIdFromContext() throws Exception {
        return userRepository.findByEmail("houseqazaqstan1@gmail.com")
                .map(User::getId)
                .orElseThrow(() -> new Exception("Табылмады брат"));

    }
}
