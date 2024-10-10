package org.example.booksservice.service;

import org.example.booksservice.dto.request.BookDto;
import org.example.booksservice.dto.response.BookResponse;
import org.example.booksservice.entity.Book;

public interface BookService {
    Book createBook(BookDto bookDto);
    BookResponse getBookWithTitle(String title, String userId);
    BookResponse getBookWithAuthor(String author);
    BookResponse getBookWithGenre(String genre);
}
