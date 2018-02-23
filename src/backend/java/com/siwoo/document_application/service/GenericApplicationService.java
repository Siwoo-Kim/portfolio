package com.siwoo.document_application.service;

import com.siwoo.document_application.exception.ApplicationDomainException;

import java.util.List;

public interface GenericApplicationService<T,ID>  {

    T save(T t);
    T edit(T t);
    T get(ID id);
    List<T> getAll();
    void delete(T t);


    default void validateNull(T t){
        if(t == null){
            throw new ApplicationDomainException("Entity is null","errors.null.domain");
        }
    }

}
