package com.siwoo.document_application.service;


import com.siwoo.document_application.domain.Review;
import com.siwoo.document_application.exception.domain.ReviewNotFoundException;
import com.siwoo.document_application.exception.service.DocumentNotFoundException;
import com.siwoo.document_application.repository.ReviewRepository;
import com.siwoo.document_application.utils.ErrorsUtil;
import com.siwoo.document_application.validator.DocumentValidator;
import com.siwoo.document_application.validator.ReviewValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.OptionalDouble;


@Slf4j
@Service @Transactional(readOnly = true)
public class ReviewServiceImpl extends AbstractApplicationService<Review,Long> implements ReviewService{

    @Autowired ReviewRepository reviewRepository;
    @Autowired ReviewValidator reviewValidator;
    @Autowired DocumentValidator documentValidator;

    @Override
    Long getDomainId(Review review) {
        return review.getId();
    }

     /*
         return the associated reviews by document id.
         RuntimeException will be fine, there is no need for handing error in application level
     */
    @Override
    public List<Review> getReviewsByDocumentId(Long documentId) {
        if(documentValidator.validateId(documentId))
        { return reviewRepository.findByDocumentId(documentId); }
        throw new DocumentNotFoundException("Document[id"+documentId+"] not found","error.notFound.document");
    }

    @Override  @Transactional(readOnly = false)
    public Review leaveReview(Review review,Errors errors) {
        reviewValidator.validateNew(review,errors);
        ErrorsUtil.showingError.setError(errors);
        if(!errors.hasErrors()) { save(review); }
        return review;
    }

    /*if the review delete return true otherwise false*/
    @Override   @Transactional(readOnly = false)
    public boolean delete(Long id) {
        return super.delete(id);
    }

    @Override   @Transactional(readOnly = false)
    public Review edit(Review review) {
        throw new UnsupportedOperationException("not implement");
    }

    /* if the review exists return review, otherwise throw ReviewNotFoundException */
    @Override
    public Review getReview(Long id) {
        if(reviewValidator.validateId(id)){ return get(id).get(); }
        throw new ReviewNotFoundException("Review[id"+id+"] not found","error.notFound.review.id");
    }

    /* Resolving average the rating for the document */
    @Override
    public OptionalDouble getRatingsForDocument(Long documentId) {
        /* might throw DocumentNotFoundException */
        List<Review> foundReviews = getReviewsByDocumentId(documentId);

        return foundReviews.stream()
                .mapToInt(Review::getRating)
                .average();
    }

    @Override
    JpaRepository<Review, Long> getRepository() {
        return reviewRepository;
    }
}

