package org.example.booksservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.booksservice.dto.response.BookResponse;
import org.example.booksservice.dto.response.ReviewRequest;
import org.example.booksservice.service.BookService;
import org.example.booksservice.service.WishListService;
import org.example.booksservice.service.UserService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishListServiceImpl implements WishListService {
    private final WebClient webClient;
    private final BookService bookService;
    private final UserService userService;

    public BookResponse saveWishList(Long bookId){
        webClient.post()
                .uri("http://localhost:8083/api/v1/wishlist/{bookId}/{userId}",bookId,userService.getUserId() )
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

}
