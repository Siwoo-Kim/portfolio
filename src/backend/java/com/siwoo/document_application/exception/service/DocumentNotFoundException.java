package com.siwoo.document_application.exception.service;

import com.siwoo.document_application.exception.ApplicationServiceException;

public class DocumentNotFoundException extends ApplicationServiceException {
    public DocumentNotFoundException(String message, String code) {
        super(message, code);
    }
}
