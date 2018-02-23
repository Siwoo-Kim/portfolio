package com.siwoo.document_application.validator;

import com.siwoo.document_application.exception.ApplicationDomainException;
import org.springframework.validation.Errors;

public interface GenericValidator  <T,ID> {

    void validateNew(T t,Errors errors);
    void validateId(ID id);


/*
    default String getDomainClassName(T t){
        return t.getClass().getSimpleName();
    }
*/

    /*validate null value of entity*/
    default void validateNull(T entity,Errors errors){
        if(entity == null){
            errors.reject("errors.null.domain");
        }
    }
}
