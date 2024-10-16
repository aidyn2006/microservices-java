package org.example.reviewservice.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequest {

    Long bookId;
    String reviewText;
    Double rating;
    Long userId;
}
