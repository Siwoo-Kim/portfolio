package com.siwoo.document_application.validator;

import com.siwoo.document_application.domain.Document;
import com.siwoo.document_application.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

@Validator @Transactional(readOnly = true)
public class DocumentValidator implements GenericValidator<Document,Long>{

    @Autowired DocumentRepository documentRepository;


    @Override
    public void validateNew(Document document, Errors errors) {
        validateNull(document,errors);
        if(!errors.hasErrors() && document.getHit() != 0){
            errors.rejectValue("hit","error.new.document.hit");
        }
    }

    @Override
    public void validateId(Long id) {

    }


}
