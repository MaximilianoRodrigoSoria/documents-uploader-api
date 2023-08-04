package com.box.documentsuploader.domain.repositories;


import com.box.documentsuploader.domain.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;
public interface DocumentRepository extends JpaRepository<Document,Long> {
}
