package org.example.booksservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.booksservice.dto.response.ReviewRequest;
import org.example.booksservice.service.ReviewService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final WebClient webClient;

    public ReviewRequest getReview(Long bookId) {
        return webClient.get()
                .uri("http://localhost:8181/api/v1/review/get-review/{bookId}", bookId)
                .retrieve()
                .bodyToMono(ReviewRequest.class)
                .block();
    }
}
