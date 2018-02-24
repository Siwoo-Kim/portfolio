package com.siwoo.document_application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Slf4j
@Entity @Table(name="sb_review")
@Getter @Setter @ToString(exclude = {"document","member"})
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@SequenceGenerator(
        name = "spring_review_generator",
        initialValue = 1,
        allocationSize = 1,
        sequenceName = "sb_review_seq")
public class Review {

    @Id @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "spring_review_generator")
    @Column(name="review_id")
    private Long id;

    private String comments;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="document_id")
    Document document;

    /*writer*/
    @ManyToOne
    @JoinColumn(name="commenter_id")
    Member commenter;

    @CreationTimestamp
    private LocalDateTime commentDate = LocalDateTime.now();

    private Integer rating = 0;

    @JsonIgnoreProperties
    public boolean setRating(Integer rating){
        boolean isValid = validateRating(rating);
        if(isValid){
            this.rating = rating;
            return isValid;
        }
        log.info("Review[rating:"+rating+"] invalid rating");
        this.rating = 0;
        return isValid;
    }


    public static boolean validateRating(Integer rating){
        return  ( rating != null && rating < 6 && rating >= 0 );
    }

    public Review(String comments, Document document, Member commenter, Integer rating, LocalDateTime commentDate) {
        this.comments = comments;
        this.document = document;
        this.commenter = commenter;
        this.commentDate = commentDate;
        this.rating = rating;
    }

}
