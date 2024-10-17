package org.example.booksservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.booksservice.dto.response.BookResponse;
import org.example.booksservice.service.IdService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/id")
@RequiredArgsConstructor
public class IdController {
    private final IdService idService;

    @PostMapping("/add-to-wishlist")
    public BookResponse addWishList(@RequestParam Long bookId){
        return idService.saveWishList(bookId);
    }

    @GetMapping("/wishlists")
    public List<BookResponse> getWishListBooks() {
        Long userId = idService.getUserId();
        return idService.getWishListBooks(userId);
    }

    @PostMapping("/subscribe-to")
    public String subscribeToGenre(@RequestParam String genre){
        idService.messageAboutSubscription(genre);
        return "You are sucsessfully subscribed";
    }
}
