package com.siwoo.document_application.service;

import com.siwoo.document_application.domain.Document;
import com.siwoo.document_application.repository.DocumentRepository;
import com.siwoo.document_application.validator.DocumentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

import java.util.Optional;


@Service("documentServiceImpl") @Transactional(readOnly = true)
public class DocumentServiceImpl extends AbstractApplicationService<Document,Long> implements DocumentService {

    @Autowired DocumentRepository documentRepository;
    @Autowired DocumentValidator documentValidator;

    /* ============  insert ===========*/
    @Transactional(readOnly = false)
    public Document save(Document document, Errors errors) {
        documentValidator.validateNew(document,errors);
        /*Only if errors does not have error, save domain.*/
        if(!errors.hasErrors()) { return save(document); }
        /*return regardless of the validation*/
        return document;
    }

    /* ============  query ===========*/
    public Document getDocument(Long id){
        Optional<Document> foundDocument = super.get(id);
        return foundDocument.orElse(null);
    }


    public Page<Document> getDocuments(Pageable pageable){
        return super.getAll(pageable);
    }


    /* ============  modify ===========*/
    @Override @Transactional(readOnly = false)
    public Document edit(Document document, Errors errors) {
        return save(document,errors);
    }

    @Override
    public boolean delete(Long id){
        return super.delete(id);
    }



    /*strategy design pattern*/
    @Override
    JpaRepository<Document, Long> getRepository() {
        return documentRepository;
    }
    /*strategy design pattern*/
    @Override
    Long getDomainId(Document document) {
        return document.getId();
    }
}
