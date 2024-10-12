package org.example.wishlistservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.wishlistservice.service.WishListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/wishlist")
@RequiredArgsConstructor
public class WishListController {

    private final WishListService wishListService;

    @PostMapping("/{bookId}/{userId}")
    public void saveDownload(@PathVariable("bookId") Long bookId, @PathVariable("userId") Long userId) {
        wishListService.saveDownload(userId, bookId);
    }
    @GetMapping("/get-wish/{userId}")
    public List<Long> getDownloads(@PathVariable Long userId) {
        return wishListService.getDownloadsByUserId(userId);
    }
}
