package com.siwoo.document_application.service;

import com.siwoo.document_application.exception.ApplicationDomainException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class GenericApplicationService<T,ID>  {

    /*generic repository*/
    abstract JpaRepository<T,ID> getRepository();
    /*get domain id*/
    abstract ID getDomainId(T t);

    T save(T entity){
        return getRepository().save(entity);
    }

    T edit(T entity){
        return getRepository().save(entity);
    }

    Optional<T> get(ID id){
        return getRepository().findById(id);
    }

    List<T> getAll(){
        return getRepository().findAll();
    }

    /*return true if domain is deleted otherwise false*/
    boolean delete(T entity){
        getRepository().delete(entity);
        return getRepository().existsById(getDomainId(entity));
    }



}
