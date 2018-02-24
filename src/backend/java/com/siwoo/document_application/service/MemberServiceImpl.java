package com.siwoo.document_application.service;

import com.siwoo.document_application.domain.Member;
import com.siwoo.document_application.repository.MemberRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

import java.util.List;

@Service @Transactional(readOnly = true)
public class MemberServiceImpl extends AbstractApplicationService<Member,Long> implements MemberService{

    private MemberRepository memberRepository;

    @Override  @Transactional(readOnly = false)
    public Member join(Member member, Errors errors) {
        return null;
    }

    @Override
    public Member getMember(String email) {
        return null;
    }

    @Override
    public Member getMemberByNickName(String nickName) {
        return null;
    }

    @Override
    public List<Member> getMembers() {
        return null;
    }

    @Override @Transactional(readOnly = false)
    public boolean delete(Long email) {
        return false;
    }


    @Override
    JpaRepository<Member, Long> getRepository() {
        return memberRepository;
    }

    @Override
    Long getDomainId(Member member) {
        return member.getId();
    }
}
