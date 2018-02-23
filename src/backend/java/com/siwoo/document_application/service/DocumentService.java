package com.siwoo.document_application.service;

import com.siwoo.document_application.domain.Document;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.Errors;

public interface DocumentService {

    Document save(Document document,Errors errors);
    Document edit(Document document,Errors errors);
    Document getAll(Pageable pageable);

}
