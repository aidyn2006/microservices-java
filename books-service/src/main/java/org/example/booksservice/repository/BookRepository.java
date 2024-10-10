package org.example.booksservice.repository;

import org.example.booksservice.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    Optional<Book> findByTitle(String title);
    Optional<Book> findAllByAuthor(String author);
    Optional<Book> findAllByGenre(String genre);
}
