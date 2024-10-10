package org.example.downloadservice.service;

import java.util.List;

public interface DownloadService {
    void saveDownload(String userId, Long bookId);
    List<Long> getDownloadsByUserId(String userId);

}
