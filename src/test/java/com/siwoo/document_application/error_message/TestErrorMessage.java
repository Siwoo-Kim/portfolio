package com.siwoo.document_application.error_message;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Locale;

import static junit.framework.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestErrorMessage {

    @Autowired MessageSource messageSource;

    @Test
    public void testResolvingErrorsMessage(){
        assertNotNull(messageSource.getMessage("error.invalid.document.tags.size",null, Locale.KOREA));
        assertNotNull(messageSource.getMessage("error.invalid.document.tags.size",null, Locale.ENGLISH));
        assertNotNull(messageSource.getMessage("error.invalid.document.tags.size",null, Locale.getDefault()));
        log.warn(messageSource.getMessage("error.invalid.document.tags.size",null,Locale.KOREA));
    }
}
