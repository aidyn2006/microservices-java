package org.example.booksservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.booksservice.dto.response.BookResponse;
import org.example.booksservice.service.DownloadService;
import org.example.booksservice.service.IdService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/download")
@RequiredArgsConstructor
public class DownloadController {
    private final DownloadService downloadService;
    private final IdService idService;

    @GetMapping("/downloadedBooks")
    public List<BookResponse> getDownloadedBooks() {
        Long userId = idService.getUserId();
        return downloadService.getDownloadedBooks(userId);
    }
}
