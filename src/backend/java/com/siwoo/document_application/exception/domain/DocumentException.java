package com.siwoo.document_application.exception.domain;

import com.siwoo.document_application.exception.ApplicationDomainException;

/*
    Representing for all document exception
*/

public class DocumentException extends ApplicationDomainException {
    public DocumentException(String message, String code) {
        super(message, code);
    }
}
