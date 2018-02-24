package com.siwoo.document_application.service;

import com.siwoo.document_application.domain.Member;
import org.springframework.validation.Errors;

import java.util.List;

public interface MemberService {

    Member join(Member member,Errors errors);
    Member getMember(String email);
    Member getMemberByNickName(String nickName);
    List<Member> getMembers();
    boolean delete(Long email);
}
