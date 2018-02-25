package com.siwoo.document_application.validator;

import com.siwoo.document_application.exception.ApplicationDomainException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;
import org.springframework.validation.Errors;

public interface GenericValidator  <T,ID> {

    void validateNew(T t,Errors errors);
    JpaRepository<T,ID> getRepository();
    Class<? extends T> getDomainClass();
    //void validateId(ID id);


    /*
        default String getDomainClassName(T t){
            return t.getClass().getSimpleName();
        }
    */


    /*
        validate null value of entity.
        if result is true, entity is valid, otherwise false
    */
    default String getDomainClassName(){
        return (getDomainClass() != null) ?  getDomainClass().getSimpleName().toLowerCase()  : null;
    }
    default boolean validateNull(T entity,Errors errors){
        if(entity != null){    return true;     }
        errors.reject("error.null."+getDomainClassName());
        return false;
    }

    default boolean validateId(ID id){
        return getRepository().existsById(id);
    }

    default boolean validateId(ID id,Errors errors){
        if(validateId(id)) {
           return true;
        }
        errors.rejectValue("id", "error.notFound." + getDomainClassName() + ".id");
        return false;
    }


}
