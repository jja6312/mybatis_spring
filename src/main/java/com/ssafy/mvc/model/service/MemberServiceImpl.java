package com.ssafy.mvc.model.service;

import com.ssafy.mvc.model.dao.MemberDao;
import com.ssafy.mvc.model.dto.member.LoginDto;
import com.ssafy.mvc.model.dto.member.Member;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{
    private final MemberDao memberDao;

    public MemberServiceImpl(MemberDao memberDao) {
        this.memberDao = memberDao;
    }


    //회원가입
    @Override
    public void createMember(Member member) {
        memberDao.createMember(member);
    }


    //로그인
    @Override
    public Member login(Member member) {
        return memberDao.login(member);
    }
}
