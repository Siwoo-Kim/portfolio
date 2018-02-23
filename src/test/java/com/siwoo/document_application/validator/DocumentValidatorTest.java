package com.siwoo.document_application.validator;

import com.siwoo.document_application.domain.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import java.util.List;
import static com.siwoo.document_application.domain.TestDocument.createDocuments;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DocumentValidatorTest {

    @Autowired DocumentValidator documentValidator;

    List<Document> documents;

    @Before
    public void setup(){
        documents = createDocuments();
    }

    @Test
    public void testValidateNew(){
        BeanPropertyBindingResult errors = new BeanPropertyBindingResult(null,"document");
        documentValidator.validateNew(null,errors);
        assertTrue("validator need to catch null",errors.hasErrors());
        assertTrue("errors.null.domain".equals(errors.getGlobalError().getCode()));
        //errors.null.domain error

        Document invalidHitDocument = documents.get(0);
        errors = new BeanPropertyBindingResult(invalidHitDocument,"document");
        invalidHitDocument.setHit(99);
        documentValidator.validateNew(invalidHitDocument,errors);
        assertTrue("validator need to catch wrong value domain",errors.hasErrors());
        assertTrue("error.new.document.hit".equals(errors.getFieldError().getCode()));
        //errors.null.domain error

    }
}
