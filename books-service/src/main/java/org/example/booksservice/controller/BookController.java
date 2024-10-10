package org.example.booksservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.booksservice.dto.request.BookDto;
import org.example.booksservice.dto.response.BookResponse;
import org.example.booksservice.entity.Book;
import org.example.booksservice.service.BookService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping(value = "/create", consumes = "multipart/form-data")
    public Book createBook(@ModelAttribute BookDto bookDto) {
        return bookService.createBook(bookDto);
    }

    @GetMapping("/get-by-title/{title}")
    public BookResponse getBookByTitle(@PathVariable("title") String title, @RequestParam("userId") String userId) {
        return bookService.getBookWithTitle(title, userId);
    }


    @GetMapping("/get-by-author/{author}")
    public BookResponse getBookByAuthor(@PathVariable("author") String author) {
        return bookService.getBookWithAuthor(author);
    }

    @GetMapping("/get-by-genre/{genre}")
    public BookResponse getBookByGenre(@PathVariable("genre") String genre) {
        return bookService.getBookWithGenre(genre);
    }



}
