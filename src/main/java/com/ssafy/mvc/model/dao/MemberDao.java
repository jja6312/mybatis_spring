package com.ssafy.mvc.model.dao;

import com.ssafy.mvc.model.dto.member.LoginDto;
import com.ssafy.mvc.model.dto.member.Member;

public interface MemberDao {
    // 회원가입
    void createMember(Member member);

    // 아이디,패스워드로 멤버 조회
    Member login(Member member);

    Member findByEmail(String email);
}
