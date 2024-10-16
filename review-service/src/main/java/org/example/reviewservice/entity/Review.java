package org.example.reviewservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Review {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "book_id")
    Long bookId;
    @Column(name = "review_text")
    String reviewText;

    Double rating;
    @Column(name = "user_id")
    Long userId;

    @CurrentTimestamp()
    LocalDateTime createdAt;
}
