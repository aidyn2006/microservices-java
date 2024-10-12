package org.example.wishlistservice.service;

import java.util.List;

public interface WishListService {
    void saveDownload(Long userId, Long bookId);
    List<Long> getDownloadsByUserId(Long userId);
}
