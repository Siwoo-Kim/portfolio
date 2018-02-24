package com.siwoo.document_application.validator;

import com.siwoo.document_application.domain.Document;
import com.siwoo.document_application.domain.Member;
import com.siwoo.document_application.domain.Review;
import com.siwoo.document_application.repository.DocumentRepository;
import com.siwoo.document_application.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ObjectError;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static com.siwoo.document_application.domain.TestDocument.createDocuments;
import static com.siwoo.document_application.domain.TestMember.createMembers;
import static com.siwoo.document_application.domain.TestReview.createReviews;
import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestReviewValidatorImpl {

    @Autowired ReviewValidator reviewValidator;
    @Autowired DocumentRepository documentRepository;
    @Autowired MemberRepository memberRepository;

    List<Review> reviews;
    List<Member> members;
    List<Document> documents;

    @Before
    public void setup(){
        documents = createDocuments();
        reviews = createReviews();
        members = createMembers();
    }

    @Test
    public void testValidateNew(){
        /*document and member is null and rating is null*/
        Review review = reviews.get(0);
        review.setRating(null);
        BeanPropertyBindingResult errors = new BeanPropertyBindingResult(review,"review");
        reviewValidator.validateNew(reviews.get(0),errors);
        assertTrue(errors.hasErrors());
        assertTrue(errors.hasGlobalErrors());

        errors.getAllErrors()
                .stream()
                .map(ObjectError::getCodes)
                .flatMap(Arrays::stream)
                .map(Object::toString)
                .forEach(log::warn);


        /*document is null member is not null but does not exists*/
        review.setRating(1);
        members.get(0).setId(99l);
        review.setCommenter(members.get(0));
        errors = new BeanPropertyBindingResult(review,"review");
        reviewValidator.validateNew(reviews.get(0),errors);
        assertTrue(errors.hasErrors());
        assertTrue(errors.hasFieldErrors("commenter"));
        assertTrue(errors.hasGlobalErrors());

        errors.getAllErrors()
                .stream()
                .map(ObjectError::getCodes)
                .flatMap(Arrays::stream)
                .map(Object::toString)
                .forEach(log::warn);

        /*member is not null and exists but document does not exists*/
        review.setRating(1);
        members.get(0).setId(null);
        memberRepository.save(members.get(0));
        review.setCommenter(members.get(0));
        documents.get(0).setId(99l);
        review.setDocument(documents.get(0));
        errors = new BeanPropertyBindingResult(review,"review");
        reviewValidator.validateNew(reviews.get(0),errors);
        assertTrue(errors.hasErrors());
        assertTrue(!errors.hasFieldErrors("commenter"));
        assertTrue(errors.hasFieldErrors("document"));

        errors.getAllErrors()
                .stream()
                .map(ObjectError::getCodes)
                .flatMap(Arrays::stream)
                .map(Object::toString)
                .forEach(log::warn);


        /*valid data*/
        review.setRating(1);
        documents.get(0).setId(null);
        documentRepository.save(documents.get(0));

        review.setDocument(documents.get(0));
        errors = new BeanPropertyBindingResult(review,"review");
        reviewValidator.validateNew(reviews.get(0),errors);

        assertTrue(!errors.hasErrors());


    }

}
