package org.example.wishlistservice.service;

import java.util.List;

public interface WishListService {
    void saveWishList(Long userId, Long bookId);
    List<Long> getWishListByUserId(Long userId);
}
