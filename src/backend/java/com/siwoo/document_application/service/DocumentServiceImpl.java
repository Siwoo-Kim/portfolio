package com.siwoo.document_application.service;

import com.siwoo.document_application.domain.Document;
import com.siwoo.document_application.repository.DocumentRepository;
import com.siwoo.document_application.validator.DocumentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;


@Service("documentServiceImpl") @Transactional(readOnly = true)
public class DocumentServiceImpl extends GenericApplicationService<Document,Long> implements DocumentService {

    @Autowired DocumentRepository documentRepository;
    @Autowired DocumentValidator documentValidator;

    @Transactional(readOnly = false)
    public Document save(Document document, Errors errors) {
        documentValidator.validateNew(document,errors);
        /*Only if errors does not have error, save domain.*/
        if(!errors.hasErrors()) { return save(document); }
        /*return regardless of the validation*/
        return document;
    }

    @Override
    public Document edit(Document document, Errors errors) {
        return null;
    }

    @Override
    public Document getAll(Pageable pageable) {
        return null;
    }


    @Override
    JpaRepository<Document, Long> getRepository() {
        return documentRepository;
    }

    @Override
    Long getDomainId(Document document) {
        return document.getId();
    }
}
