package com.siwoo.document_application.validator;

import com.siwoo.document_application.domain.Review;
import com.siwoo.document_application.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.validation.Errors;

@Validator
public class ReviewValidator implements GenericValidator<Review,Long> {

    @Autowired MemberValidator memberValidator;
    @Autowired DocumentValidator documentValidator;
    @Autowired ReviewRepository reviewRepository;

    @Override
    public void validateNew(Review review, Errors errors) {
        //Relation entity should not be null or not exists in database
        if(memberValidator.validateNull(review.getCommenter(),errors)){
            if(!memberValidator.validateId(review.getCommenter().getId())){
                errors.rejectValue("commenter","error.notFound.review.commenter");
            };
        };

        if(documentValidator.validateNull(review.getDocument(),errors)){
            if(!documentValidator.validateId(review.getDocument().getId())){
                errors.rejectValue("document","error.notFound.review.document");
            }
        }

        if(review.getRating() == null || !Review.validateRating(review.getRating())){
            errors.rejectValue("rating","error.invalid.review.rating");
        }
    }


    @Override
    public JpaRepository<Review, Long> getRepository() {
        return reviewRepository;
    }

    @Override
    public Class<? extends Review> getDomainClass() {
        return Review.class;
    }

}
