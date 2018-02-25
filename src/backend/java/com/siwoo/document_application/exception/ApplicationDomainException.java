package com.siwoo.document_application.exception;


/*
    Representing for all domain exception
*/

public class ApplicationDomainException extends RuntimeException {
    /*
        Code is used for resolving application error message
        Error Message will be retrieved by MessageSource Bean
    */
    String code;

    @FunctionalInterface
    public interface DomainExceptionConstructor<T extends ApplicationDomainException>{
        T setErrorDetail(Object field,String code);
    }

    public ApplicationDomainException(String message, String code) {
        super(message);
        this.code = code;
    }
}
