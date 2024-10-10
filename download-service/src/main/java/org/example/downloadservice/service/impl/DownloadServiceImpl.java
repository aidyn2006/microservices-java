package org.example.downloadservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.downloadservice.entity.Download;
import org.example.downloadservice.repository.DownloadRepository;
import org.example.downloadservice.service.DownloadService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DownloadServiceImpl implements DownloadService {

    private final DownloadRepository downloadRepository;

    public void saveDownload(String userId, Long bookId) {
        Download download= Download.builder()
                .bookid(bookId)
                .userId(userId)
                .build();
        downloadRepository.save(download);


    }
}
