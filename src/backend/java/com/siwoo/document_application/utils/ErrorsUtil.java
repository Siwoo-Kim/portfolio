package com.siwoo.document_application.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import java.util.Arrays;
import java.util.function.Consumer;

/*
    org.springframework.validation.Errors Util Class
*/

@Slf4j
public class ErrorsUtil {

    /*
        The interface consumes the errors object to show the error purpose
    */
    @FunctionalInterface
    public interface ShowError{
        void setError(Errors errors);
    }

    public static ShowError showingError = (errors) -> {
        errors.getAllErrors()
                .stream()
                .map(ObjectError::getCodes)
                .flatMap(Arrays::stream)
                .map(Object::toString)
                .forEach(log::warn);
    };

}
