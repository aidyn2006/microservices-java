package org.example.booksservice.service;

import org.example.booksservice.dto.request.BookDto;
import org.example.booksservice.dto.response.BookResponse;
import org.example.booksservice.dto.response.BookResponseWithReview;
import org.example.booksservice.entity.Book;

import java.util.List;

public interface BookService {
    Book createBook(BookDto bookDto);

    List<BookResponse> getBooksByIds(List<Long> bookIds);
    BookResponse mapBookToResponse(Book book);
}
