package org.example.downloadservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.downloadservice.service.DownloadService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/downloads")
@RequiredArgsConstructor
public class DownloadController {

    private final DownloadService downloadService;

    @PostMapping("/{bookId}/{userId}")
    public void saveDownload(@PathVariable("bookId") Long bookId, @PathVariable("userId") Long userId) {
        downloadService.saveDownload(userId, bookId);
    }
    @GetMapping("/getDownloads/{userId}")
    public List<Long> getDownloads(@PathVariable Long userId) {
        return downloadService.getDownloadsByUserId(userId);
    }
}
