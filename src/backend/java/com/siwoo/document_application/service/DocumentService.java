package com.siwoo.document_application.service;

import com.siwoo.document_application.domain.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.Errors;

import java.util.List;

public interface DocumentService {

    Document save(Document document,Errors errors);
    Document edit(Document document,Errors errors);
    Page<Document> getDocuments(Pageable pageable);
    Document getDocument(Long id);
    boolean delete(Long id);

}
