package org.example.reviewservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.reviewservice.dto.ReviewRequest;
import org.example.reviewservice.service.ReviewService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/comment-and-rate")
    public String commentAndRate(@RequestBody ReviewRequest reviewRequest){
        reviewService.saveReview(reviewRequest);
        return "Saved";
    }

    @GetMapping("/get-review/{bookId}")
    public ReviewRequest getReview(@PathVariable("bookId") Long bookId){
        return reviewService.getReview(bookId);
    }

}
