package com.siwoo.document_application.service;

import com.siwoo.document_application.domain.Document;
import com.siwoo.document_application.repository.DocumentRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import java.util.List;
import static com.siwoo.document_application.domain.TestDocument.createDocuments;
import static org.junit.Assert.*;

@Slf4j
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDocumentServiceImpl {

    @Autowired DocumentService documentService;
    @Autowired DocumentRepository documentRepository;
    List<Document> documents;

    @Before
    public void setup(){
        documents = createDocuments();
    }

    @Test
    public void testSave(){

        BeanPropertyBindingResult errors = new BeanPropertyBindingResult(null,"document");
        documentService.save(null,errors);
        assertTrue("Null should be catched",errors.hasErrors());

        errors = new BeanPropertyBindingResult(documents.get(0),"document");
        documentService.save(documents.get(0),errors);
        assertTrue(!errors.hasErrors());
        assertTrue("If error does not exist, document should be saved",
                documents.get(0) == documentRepository
                        .findById(documents.get(0).getId()).get());
        log.warn(documentRepository.findById(documents.get(0).getId()).get().toString());

    }

    @Test
    public void testGetDocument(){

        documentRepository.save(documents.get(0));
        Document document = documentService.getDocument(documents.get(0).getId());
        assertNotNull(document);
        log.warn(document.toString());
        assertNull(documentService.getDocument(999L));

    }

    @Test
    public void testDelete(){

        documentRepository.save(documents.get(0));
        Long id = documents.get(0).getId();
        assertTrue(documentRepository.existsById(id));

        assertTrue(documentService.delete(id));
        assertFalse(documentRepository.existsById(id));
    }

    @Test
    public void getDocuments(){
        documentRepository.saveAll(documents);

        documentService.getDocuments(Pageable.unpaged()).stream()
                .map(Document::toString).forEach(log::warn);


    }
}
