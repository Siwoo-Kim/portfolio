package com.siwoo.document_application.service;

import com.siwoo.document_application.domain.Document;
import com.siwoo.document_application.domain.Review;
import org.springframework.validation.Errors;

import java.util.List;

public interface ReviewService {
    List<Document> getReviewsByDocumentId(Long documentId);
    Review leaveReview(Review review,Errors errors);
    boolean delete(Long id);
    Review edit(Review review);
    Review getReview(Long id);
    Double getRatingsForDocument(Long documentId);
}
