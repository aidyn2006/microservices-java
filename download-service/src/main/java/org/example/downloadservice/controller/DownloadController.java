package org.example.downloadservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.downloadservice.service.DownloadService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/downloads")
@RequiredArgsConstructor
public class DownloadController {

    private final DownloadService downloadService;

    @PostMapping("/{bookId}")
    public void saveDownload(@PathVariable("bookId") Long bookId, @RequestParam("userId") String userId) {
        downloadService.saveDownload(userId, bookId);
    }
}
