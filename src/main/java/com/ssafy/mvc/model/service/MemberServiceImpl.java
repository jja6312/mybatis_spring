package com.ssafy.mvc.model.service;

import com.ssafy.mvc.model.dao.MemberDao;
import com.ssafy.mvc.model.dto.member.Member;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{
    private final MemberDao memberDao;

    public MemberServiceImpl(MemberDao memberDao) {
        this.memberDao = memberDao;
    }


    @Override
    public void createMember(Member member) {
        memberDao.createMember(member);
    }
}
