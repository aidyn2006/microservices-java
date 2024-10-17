package org.example.booksservice.service;

import org.example.booksservice.dto.response.BookResponse;
import org.example.booksservice.dto.response.ReviewRequest;

import java.util.List;

public interface WishListService {
    BookResponse saveWishList(Long bookId);
    List<BookResponse> getWishListBooks(Long userId);


}
