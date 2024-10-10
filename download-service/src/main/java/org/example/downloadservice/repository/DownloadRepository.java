package org.example.downloadservice.repository;

import org.example.downloadservice.entity.Download;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.nio.file.LinkOption;

@Repository
public interface DownloadRepository extends JpaRepository<Download, Long> {
}
