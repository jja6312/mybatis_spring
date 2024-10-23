package com.ssafy.mvc.model.dao;

import com.ssafy.mvc.model.dto.member.LoginDto;
import com.ssafy.mvc.model.dto.member.Member;
import com.ssafy.mvc.model.dto.member.MemberSecuInfo;
import com.ssafy.mvc.model.dto.member.MemberWithoutValid;

public interface MemberDao {
    // 회원가입
    void insertMember(Member member);

    // 아이디,패스워드로 멤버 조회
    Member login(Member member);

    MemberWithoutValid findByEmail(String email);



}
