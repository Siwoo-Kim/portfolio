package com.siwoo.document_application.service;

import com.siwoo.document_application.domain.Document;
import com.siwoo.document_application.domain.Member;
import com.siwoo.document_application.domain.Review;
import com.siwoo.document_application.exception.domain.ReviewNotFoundException;
import com.siwoo.document_application.exception.service.DocumentNotFoundException;
import com.siwoo.document_application.repository.DocumentRepository;
import com.siwoo.document_application.repository.MemberRepository;
import com.siwoo.document_application.repository.ReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;

import java.util.List;
import java.util.OptionalDouble;

import static com.siwoo.document_application.domain.TestDocument.createDocuments;
import static com.siwoo.document_application.domain.TestMember.createMembers;
import static com.siwoo.document_application.domain.TestReview.createReviews;
import static org.junit.Assert.*;

@Slf4j
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestReviewServiceImpl {

    @Autowired ReviewRepository reviewRepository;
    @Autowired DocumentRepository documentRepository;
    @Autowired MemberRepository memberRepository;
    @Autowired ReviewService reviewService;

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
    public void testLeaveReview(){
        documentRepository.save(documents.get(0));
        memberRepository.save(members.get(0));
        Review review = reviews.get(0);
        review.setDocument(documents.get(0));
        review.setCommenter(members.get(0));

        BeanPropertyBindingResult errors = new BeanPropertyBindingResult(review,"review");
        reviewService.leaveReview(review,errors);

        Review foundReview = reviewRepository.findById(review.getId()).orElse(null);
        assertTrue(!errors.hasErrors());
        assertNotNull("Review should be saved",foundReview);
        log.warn(foundReview.toString());
    }

    @Test
    public void testDelete(){
        Review review = reviews.get(0);
        reviewRepository.save(review);

        assertTrue(reviewService.delete(review.getId()));
        assertFalse(reviewService.delete(review.getId()));
    }


    @Test(expected = DocumentNotFoundException.class)
    public void testGetReviewsDocumentId(){
        documentRepository.save(documents.get(0));
        reviews.get(0).setDocument(documents.get(0));
        reviews.get(1).setDocument(documents.get(0));
        reviewRepository.save(reviews.get(0));
        reviewRepository.save(reviews.get(1));

        List<Review> foundReviews = reviewService.getReviewsByDocumentId(documents.get(0).getId());
        assertTrue("Resolving review should be 2", foundReviews.size()==2);

        reviewService.getReviewsByDocumentId(99L);
        fail("expect DocumentNotFoundException");
    }

    @Test
    public void testRatingsForDocument(){
        documentRepository.save(documents.get(0));
        reviews.get(0).setRating(3);
        reviews.get(0).setDocument(documents.get(0));
        reviews.get(1).setRating(2);
        reviews.get(1).setDocument(documents.get(0));
        reviews.get(2).setRating(4);
        reviews.get(2).setDocument(documents.get(0));
        reviewRepository.save(reviews.get(0));
        reviewRepository.save(reviews.get(1));
        reviewRepository.save(reviews.get(2));
        Long documentId = documents.get(0).getId();

        OptionalDouble average = reviewService.getRatingsForDocument(documentId);
        assertTrue(average.isPresent());
        assertEquals(average.getAsDouble() , ( (3+2+4)/3 ) , 0 ); //마지막 인자는 오차값.

    }

    @Test(expected = ReviewNotFoundException.class)
    public void getReview(){
        reviewRepository.save(reviews.get(0));
        Review foundReview = reviewService.getReview(reviews.get(0).getId());
        assertNotNull(foundReview);

        reviewService.getReview(99l);
        fail("expect ReviewNotFoundException");
    }
}
