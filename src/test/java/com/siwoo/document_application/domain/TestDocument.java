package com.siwoo.document_application.domain;

import com.siwoo.document_application.exception.domain.DocumentTagsException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;

@Slf4j
public class TestDocument {

    @Test(expected = DocumentTagsException.class)
    public void testValidateTags(){
        List<String> tags = new ArrayList<>();
        tags.add("tag1");
        tags.add("tag2");
        tags.add("tag3");
        Document document = new Document();
        document.setTags(tags);
        assertNotNull(document.getTags());
        log.warn(document.getTags().toString());

        tags.add("tag4");
        document.setTags(tags);
        fail("Tag element should not exceed 4");
    }

    @Test
    public void testAddTag(){
        List<String> tags = new ArrayList<>();
        tags.add("tag1");
        tags.add("tag2");
        tags.add("tag3");
        Document document = new Document();
        document.setTags(tags);
        assertFalse("AddTag should return false", document.addTag("newTag") );

        String addedTag = "newTag";
        document.getTags().remove(0);

        assertTrue("If tags has enough size, tag should be added",document.addTag(addedTag));
        assertTrue(document.getTags().contains(addedTag));
    }

    @Test
    public void testDefaultState(){
        Document document = new Document();
        assertTrue("default hit must be 0",document.getHit()==0);
        assertTrue("ratings must be null(not 0)",document.getRatings() == null);
        assertTrue("default secret is false",!document.isSecret());
        //assertTrue("ratings must be null(not 0)",document.getRatings() != .0);
    }


    public static List<Document> createDocuments(){
        return Arrays.asList(
            new Document("title1","text1",false,null,0,null, null),
                new Document("title2","text2",false,null,0,null, null),
                new Document("title3","text3",false,null,0,null, null),
                new Document("title4","text4",false,null,0,null, null),
                new Document("title5","text5",false,null,0,null, null),
                new Document("title6","text6",false,null,0,null, null),
                new Document("title7","text7",false,null,0,null, null)
        );
    }
}
