package org.example.reviewservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.reviewservice.dto.ReviewRequest;
import org.example.reviewservice.entity.Review;
import org.example.reviewservice.repository.ReviewRepository;
import org.example.reviewservice.service.ReviewService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Transactional
    public void saveReview(ReviewRequest reviewRequest){
        Review review= Review.builder()
                .bookId(reviewRequest.getBookId())
                .reviewText(reviewRequest.getReviewText())
                .userId(reviewRequest.getUserId())
                .rating(reviewRequest.getRating())
                .createdAt(LocalDateTime.now())
                .build();
        reviewRepository.save(review);
    }

    public ReviewRequest getReview(Long bookId){
        Review review=reviewRepository.findByBookId(bookId)
                .orElseThrow(()->new RuntimeException("Not found"));
        return ReviewRequest.builder()
                .bookId(review.getBookId())
                .rating(review.getRating())
                .reviewText(review.getReviewText())
                .userId(review.getUserId())
                .build();
    }

}
