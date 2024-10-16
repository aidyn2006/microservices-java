package org.example.reviewservice.service;


import org.example.reviewservice.dto.ReviewRequest;

public interface ReviewService {
    void saveReview(ReviewRequest reviewRequest);
    ReviewRequest getReview(Long bookId);

}
