package org.example.booksservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.booksservice.service.UserService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final WebClient webClient;

    @Override
    public Long getUserId() {
        return webClient.get()
                .uri("http://localhost:7070/api/v1/auth/get-user-id")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Long>() {})
                .block();
    }

    @Override
    public void sendMessage() {
        webClient.post()
                .uri("http://localhost:7070/api/v1/email/send-message")
                .retrieve()
                .bodyToMono(String.class)
                .subscribe();
    }
}

