package org.example.booksservice.service;

import org.example.booksservice.dto.request.BookDto;
import org.example.booksservice.dto.response.BookResponse;
import org.example.booksservice.dto.response.BookResponseWithReview;
import org.example.booksservice.entity.Book;

import java.util.List;

public interface BookService {
    Book createBook(BookDto bookDto);

    BookResponseWithReview getBookWithTitle(String title);
    BookResponse getBookWithAuthor(String author);

    BookResponse getBookWithGenre(String genre);

    List<BookResponse> getDownloadedBooks(Long userId);
    Long getUserId();
    BookResponse saveWishList(Long bookId);
    List<BookResponse> getWishListBooks(Long userId);
    void messageAboutSubscription(String genre);
}
