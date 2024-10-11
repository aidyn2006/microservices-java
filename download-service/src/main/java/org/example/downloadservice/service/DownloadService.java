package org.example.downloadservice.service;

import java.util.List;

public interface DownloadService {
    void saveDownload(Long userId, Long bookId);
    List<Long> getDownloadsByUserId(Long userId);
}
