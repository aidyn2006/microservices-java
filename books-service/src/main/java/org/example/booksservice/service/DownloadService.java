package org.example.booksservice.service;

import org.example.booksservice.dto.response.BookResponse;

import java.util.List;

public interface DownloadService {
    List<BookResponse> getDownloadedBooks(Long userId);
    void saveDownload(Long bookId,Long userId);
}
