package com.ssafy.mvc.model.validator.member;

import com.ssafy.mvc.model.dto.member.LoginDto;
import com.ssafy.mvc.model.validator.Validator;
import org.springframework.stereotype.Component;


@Component
public class LoginDtoValidator implements Validator<LoginDto> {

    @Override
    public boolean validate(LoginDto loginDto) {
        // 이메일 null 방지
        if(loginDto.getEmail() == null || loginDto.getEmail().trim().equals("")) return false;
        // 패스워드 null 방지
        else if(loginDto.getPassword() == null || loginDto.getPassword().trim().equals("")) return false;

        return true;
    }
}
