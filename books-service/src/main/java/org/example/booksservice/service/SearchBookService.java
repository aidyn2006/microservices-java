package org.example.booksservice.service;

import org.example.booksservice.dto.response.BookResponse;
import org.example.booksservice.dto.response.BookResponseWithReview;

public interface SearchBookService {
    BookResponseWithReview getBookWithTitle(String title);
    BookResponse getBookWithAuthor(String author);

    BookResponse getBookWithGenre(String genre);

}
