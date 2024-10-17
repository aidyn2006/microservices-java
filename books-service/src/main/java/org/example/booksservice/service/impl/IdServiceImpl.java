package org.example.booksservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.booksservice.dto.response.BookResponse;
import org.example.booksservice.dto.response.ReviewRequest;
import org.example.booksservice.service.BookService;
import org.example.booksservice.service.IdService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IdServiceImpl implements IdService {
    private final WebClient webClient;
    private final BookService bookService;

    public BookResponse saveWishList(Long bookId){
        webClient.post()
                .uri("http://localhost:8083/api/v1/wishlist/{bookId}/{userId}",bookId,getUserId())
                .retrieve()
                .bodyToMono(BookResponse.class)
                .subscribe();
        return null;
    }

    public List<BookResponse> getWishListBooks(Long userId) {
        List<Long> downloadedBookIds = webClient.get()
                .uri("http://localhost:8083/api/v1/wishlist/get-wish/{userId}", userId)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Long>>() {})
                .block();
        return bookService.getBooksByIds(downloadedBookIds);
    }
    public Long getUserId(){
        Long userId=webClient.get()
                .uri("http://localhost:7070/api/v1/auth/get-user-id")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Long>() {
                })
                .block();
        return userId;
    }
    public ReviewRequest getReview(Long bookId) {
        return webClient.get()
                .uri("http://localhost:8181/api/v1/review/get-review/{bookId}", bookId)
                .retrieve()
                .bodyToMono(ReviewRequest.class)
                .block();
    }
    public void messageAboutSubscription(String genre) {
        webClient.post()
                .uri("http://localhost:8888/api/v1/subscription/{genre}/{userId}", genre, getUserId())
                .retrieve()
                .bodyToMono(String.class)
                .doOnError(error -> {
                    System.err.println("Error during subscription: " + error.getMessage());
                })
                .subscribe(response -> {
                    System.out.println("Subscription response: " + response);
                });
    }
    public String getSubscribers() {
        return webClient.get()
                .uri("http://localhost:8888/api/v1/subscription/{userId}", getUserId())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
    public void sendMessage(){
        webClient.post()
                .uri("http://localhost:7070/api/v1/auth/send-message")
                .retrieve()
                .bodyToMono(String.class)
                .subscribe();
    }

}
