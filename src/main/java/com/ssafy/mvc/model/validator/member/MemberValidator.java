package com.ssafy.mvc.model.validator.member;

import com.ssafy.mvc.model.dto.Dto;
import com.ssafy.mvc.model.dto.member.LoginDto;
import com.ssafy.mvc.model.dto.member.Member;
import com.ssafy.mvc.model.validator.Validator;
import org.springframework.stereotype.Component;

@Component
public class MemberValidator implements Validator<Member> {

    @Override
    public boolean validate(Member member) {
        // 이메일 null 방지
        if(member.getEmail() == null || member.getEmail().trim().equals("")) return false;
        // 패스워드 null 방지
        else if(member.getPassword() == null || member.getPassword().trim().equals("")) return false;
        // 이름 null 방지
        else if(member.getName() == null || member.getName().trim().equals("")) return false;

        return true;
    }
}
