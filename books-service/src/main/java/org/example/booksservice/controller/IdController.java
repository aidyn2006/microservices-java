package org.example.booksservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.booksservice.dto.response.BookResponse;
import org.example.booksservice.service.UserService;
import org.example.booksservice.service.WishListService;
import org.example.booksservice.service.NotificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/id")
@RequiredArgsConstructor
public class IdController {
    private final UserService userService;
    private final WishListService wishListService;
    private final NotificationService notificationService;

    @PostMapping("/add-to-wishlist")
    public BookResponse addWishList(@RequestParam Long bookId){
        return wishListService.saveWishList(bookId);
    }

    @GetMapping("/wishlists")
    public List<BookResponse> getWishListBooks() {
        Long userId = userService.getUserId();
        return wishListService.getWishListBooks(userId);
    }

    @PostMapping("/subscribe-to")
    public String subscribeToGenre(@RequestParam String genre){
        notificationService.messageAboutSubscription(genre);
        return "You are sucsessfully subscribed";
    }
}
