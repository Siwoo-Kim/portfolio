package com.siwoo.document_application.repository;

import com.siwoo.document_application.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {

}
