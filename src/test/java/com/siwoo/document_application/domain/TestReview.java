package com.siwoo.document_application.domain;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TestReview {

    @Test
    public void testSetRating(){
        Review review = new Review();
        assertFalse("SetRating() need to return false for wrong value",review.setRating(6));
        assertTrue("SetRating() need to set 0 for wrong value",review.getRating()==0);
    }

    public static List<Review> createReviews(){
        return Arrays.asList(
                new Review("review1",null,null,1, null),
                new Review("review2",null,null,1, null),
                new Review("review3",null,null,1, null),
                new Review("review4",null,null,1, null),
                new Review("review5",null,null,1, null)
        );
    }

}
