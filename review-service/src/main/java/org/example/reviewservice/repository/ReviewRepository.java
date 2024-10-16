package org.example.reviewservice.repository;

import org.example.reviewservice.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {

    Optional<Review> findByBookId(Long bookId);
}
