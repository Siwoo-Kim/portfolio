package com.siwoo.document_application.exception.domain;

import com.siwoo.document_application.exception.ApplicationDomainException;
import org.springframework.util.StringUtils;

import java.util.function.Supplier;

public class ReviewNotFoundException extends ApplicationDomainException{


    public ReviewNotFoundException(String message, String code) {
        super(message, code);
    }
}
