package com.siwoo.document_application.exception.domain;

import com.siwoo.document_application.exception.domain.DocumentException;

/*
    Document Domain Exception
    : 1. tag size is wrong
*/

public class DocumentTagsException extends DocumentException {
    public DocumentTagsException(String message, String code) {
        super(message, code);
    }
}
