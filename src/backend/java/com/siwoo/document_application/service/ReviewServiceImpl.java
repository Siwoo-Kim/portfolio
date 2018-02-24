package com.siwoo.document_application.service;


import com.siwoo.document_application.domain.Document;
import com.siwoo.document_application.domain.Review;
import com.siwoo.document_application.repository.ReviewRepository;
import com.siwoo.document_application.validator.DocumentValidator;
import com.siwoo.document_application.validator.MemberValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import java.util.List;

@Service @Transactional(readOnly = true)
public class ReviewServiceImpl extends AbstractApplicationService<Review,Long> implements ReviewService{

    @Autowired ReviewRepository reviewRepository;
    @Autowired DocumentValidator documentValidator;
    @Autowired MemberValidator memberValidator;

    @Override
    JpaRepository<Review, Long> getRepository() {
        return reviewRepository;
    }

    @Override
    Long getDomainId(Review review) {
        return review.getId();
    }

    @Override
    public List<Document> getReviewsByDocumentId(Long documentId) {
        return null;
    }

    @Override
    public Review leaveReview(Review review,Errors errors) {

        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public Review edit(Review review) {
        return null;
    }

    @Override
    public Review getReview(Long id) {
        return null;
    }

    @Override
    public Double getRatingsForDocument(Long documentId) {
        return null;
    }
}
