package com.siwoo.document_application.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

@Slf4j
public abstract class AbstractApplicationService<T,ID>  {

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

    /*Return Entities with Collection: Page or List*/
    Page<T> getAll(Pageable pageable){
        return getRepository().findAll(pageable);
    }

    List<T> getAll(){
        return getRepository().findAll();
    }

    /*return true if domain is deleted otherwise false*/
    boolean delete(ID id){
        if(id == null) return false;
        if(!get(id).isPresent()) return false;

        getRepository().deleteById(id);
        return true;
    }



}
