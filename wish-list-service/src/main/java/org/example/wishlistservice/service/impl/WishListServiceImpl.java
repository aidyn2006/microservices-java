package org.example.wishlistservice.service.impl;

import lombok.RequiredArgsConstructor;

import org.example.wishlistservice.entity.WishList;
import org.example.wishlistservice.repository.WishListRepository;
import org.example.wishlistservice.service.WishListService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishListServiceImpl implements WishListService {

    private final WishListRepository wishListRepository;

    public void saveDownload(Long userId, Long bookId) {
        WishList wishList= WishList.builder()
                .bookId(bookId)
                .userId(userId)
                .build();
        wishListRepository.save(wishList);
    }

    public List<Long> getDownloadsByUserId(Long userId) {
        return wishListRepository.findAllByUserId(userId)
                .stream()
                .map(WishList::getBookId)
                .collect(Collectors.toList());
    }


}
