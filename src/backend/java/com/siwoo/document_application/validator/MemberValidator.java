package com.siwoo.document_application.validator;

import com.siwoo.document_application.domain.Member;
import com.siwoo.document_application.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.validation.Errors;

@Validator
public class MemberValidator implements GenericValidator<Member,Long>{

    @Autowired MemberRepository memberRepository;
    @Override
    public void validateNew(Member member, Errors errors) {

    }


    /*strategy design pattern*/
    @Override
    public JpaRepository<Member, Long> getRepository() {
        return memberRepository;
    }

    @Override
    public Class<? extends Member> getDomainClass() {
        return Member.class;
    }

}
