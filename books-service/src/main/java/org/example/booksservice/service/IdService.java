package org.example.booksservice.service;

import org.example.booksservice.dto.response.BookResponse;
import org.example.booksservice.dto.response.ReviewRequest;

import java.util.List;

public interface IdService {
    Long getUserId();
    BookResponse saveWishList(Long bookId);
    List<BookResponse> getWishListBooks(Long userId);
    ReviewRequest getReview(Long bookId);
    void messageAboutSubscription(String genre);
    String getSubscribers();
    void sendMessage();

}
