package com.siwoo.document_application.service;

import com.siwoo.document_application.domain.Document;
import com.siwoo.document_application.domain.Review;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.OptionalDouble;

public interface ReviewService {
    /*
       return the associated reviews by document id.
       RuntimeException will be fine, there is no need for handing error in application level
   */
    List<Review> getReviewsByDocumentId(Long documentId);
    Review leaveReview(Review review,Errors errors);
    /* if the review delete return true otherwise false */
    boolean delete(Long id);
    Review edit(Review review);
    Review getReview(Long id);
    /* Resolving average the rating for the document */
    OptionalDouble getRatingsForDocument(Long documentId);
}
