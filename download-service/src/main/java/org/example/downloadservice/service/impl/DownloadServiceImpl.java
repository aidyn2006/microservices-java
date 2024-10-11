package org.example.downloadservice.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.downloadservice.entity.Download;
import org.example.downloadservice.repository.DownloadRepository;
import org.example.downloadservice.service.DownloadService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DownloadServiceImpl implements DownloadService {

    private final DownloadRepository downloadRepository;

    public void saveDownload(Long userId, Long bookId) {
        Download download= Download.builder()
                .bookId(bookId)
                .userId(userId)
                .build();
        downloadRepository.save(download);
    }

    public List<Long> getDownloadsByUserId(Long userId) {
        return downloadRepository.findAllByUserId(userId)
                .stream()
                .map(Download::getBookId)
                .collect(Collectors.toList());
    }


}
