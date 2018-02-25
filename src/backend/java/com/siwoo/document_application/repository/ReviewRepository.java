package com.siwoo.document_application.repository;

import com.siwoo.document_application.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository  extends JpaRepository<Review,Long>{

    List<Review> findByDocumentId(Long documentId);

}
