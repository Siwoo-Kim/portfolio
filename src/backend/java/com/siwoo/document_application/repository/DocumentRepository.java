package com.siwoo.document_application.repository;

import com.siwoo.document_application.domain.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document,Long> {


}
