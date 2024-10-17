package org.example.booksservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.booksservice.dto.response.BookResponse;
import org.example.booksservice.service.BookService;
import org.example.booksservice.service.DownloadService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DownloadServiceImpl implements DownloadService {
    private final WebClient webClient;
    private final BookService bookService;

    public List<BookResponse> getDownloadedBooks(Long userId) {
        List<Long> downloadedBookIds = webClient.get()
                .uri("http://localhost:8081/api/v1/downloads/getDownloads/{userId}", userId)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Long>>() {})
                .block();
        return bookService.getBooksByIds(downloadedBookIds);
    }
    public void saveDownload(Long bookId, Long userId){
        webClient.post()
                .uri("http://localhost:8081/api/v1/downloads/{bookId}/{userId}", bookId, userId)
                .retrieve()
                .bodyToMono(Void.class)
                .doOnError(error -> {
                    System.out.println("Ошибка при сохранении загрузки: " + error.getMessage());
                })
                .subscribe();

    }

}
