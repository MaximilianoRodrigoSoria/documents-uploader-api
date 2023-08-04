package com.box.documentsuploader.domain.repositories;


import com.box.documentsuploader.domain.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface DocumentRepository extends JpaRepository<Document,Long> {

    Optional<Document> findDocumentByAccountIdAndHashSha256OrHashSha512(Long accountId, String sha256,String sha512);
    Optional<Set<Document>>findDocumentByAccountId(Long accountId);

    Optional<Document> findByAccountIdAndFileName(Long accountId, String filename);
}
