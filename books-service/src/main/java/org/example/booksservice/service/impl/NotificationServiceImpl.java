package org.example.booksservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.booksservice.service.UserService;
import org.example.booksservice.service.NotificationService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final WebClient webClient;
    private final UserService userService;

    public void messageAboutSubscription(String genre) {
        webClient.post()
                .uri("http://localhost:8888/api/v1/subscription/{genre}/{userId}", genre, userService.getUserId())
                .retrieve()
                .bodyToMono(String.class)
                .doOnError(error -> {
                    System.err.println("Error during subscription: " + error.getMessage());
                })
                .subscribe(response -> {
                    System.out.println("Subscription response: " + response);
                });
    }
    public List<String> getSubscribers() {
        String response = webClient.get()
                .uri("http://localhost:8888/api/v1/subscription/{userId}", userService.getUserId())
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return Collections.singletonList(response.replaceAll("[\\[\\]\"]", ""));
    }

}