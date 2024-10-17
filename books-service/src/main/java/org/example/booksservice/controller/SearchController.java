package org.example.booksservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.booksservice.dto.response.BookResponse;
import org.example.booksservice.dto.response.BookResponseWithReview;
import org.example.booksservice.service.SearchBookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchBookService searchBookService;


    @GetMapping("/get-by-title/{title}")
    public BookResponseWithReview getBookByTitle(@PathVariable("title") String title) {
        return searchBookService.getBookWithTitle(title);
    }

    @GetMapping("/get-by-author/{author}")
    public BookResponse getBookByAuthor(@PathVariable("author") String author) {
        return searchBookService.getBookWithAuthor(author);
    }

    @GetMapping("/get-by-genre/{genre}")
    public BookResponse getBookByGenre(@PathVariable("genre") String genre) {
        return searchBookService.getBookWithGenre(genre);
    }
}
