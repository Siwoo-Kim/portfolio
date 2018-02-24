package com.siwoo.document_application.exception;

public class ApplicationServiceException extends RuntimeException {
    public String code;

    public ApplicationServiceException(String message, String code) {
        super(message);
        this.code = code;
    }
}
