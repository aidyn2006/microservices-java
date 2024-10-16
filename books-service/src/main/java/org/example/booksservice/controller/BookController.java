package org.example.booksservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.booksservice.dto.request.BookDto;
import org.example.booksservice.dto.response.BookResponse;
import org.example.booksservice.entity.Book;
import org.example.booksservice.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public BookResponse getBookByTitle(@PathVariable("title") String title) {
        return bookService.getBookWithTitle(title);
    }


    @GetMapping("/get-by-author/{author}")
    public BookResponse getBookByAuthor(@PathVariable("author") String author) {
        return bookService.getBookWithAuthor(author);
    }

    @GetMapping("/get-by-genre/{genre}")
    public BookResponse getBookByGenre(@PathVariable("genre") String genre) {
        return bookService.getBookWithGenre(genre);
    }
    @GetMapping("/downloadedBooks")
    public List<BookResponse> getDownloadedBooks() {
        Long userId = bookService.getUserId();
        return bookService.getDownloadedBooks(userId);
    }
    @PostMapping("/add-to-wishlist")
    public BookResponse addWishList(@RequestParam Long bookId){
        return bookService.saveWishList(bookId);
    }

    @GetMapping("/wishlists")
    public List<BookResponse> getWishListBooks() {
        Long userId = bookService.getUserId();
        return bookService.getWishListBooks(userId);
    }

    @PostMapping("/subscribe-to")
    public String subscribeToGenre(@RequestParam String genre){
        bookService.messageAboutSubscription(genre);
        return "You are sucsessfully subscribed";
    }


}
