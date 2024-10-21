package com.ssafy.mvc.model.service;

import com.ssafy.mvc.model.dao.MemberDao;
import com.ssafy.mvc.model.dto.member.LoginDto;
import com.ssafy.mvc.model.dto.member.Member;
import org.springframework.stereotype.Service;

public interface MemberService {
    void createMember(Member member);

    Member login(Member member);
}
