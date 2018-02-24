package com.siwoo.document_application.repository;

import com.siwoo.document_application.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository  extends JpaRepository<Review,Long>{

}
