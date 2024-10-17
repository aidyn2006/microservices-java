package org.example.booksservice.service;

import org.example.booksservice.dto.response.ReviewRequest;

public interface ReviewService {
    ReviewRequest getReview(Long bookId);

}
