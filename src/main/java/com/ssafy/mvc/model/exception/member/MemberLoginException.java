package com.ssafy.mvc.model.exception.member;

import com.ssafy.mvc.model.dto.member.Member;

public class MemberLoginException extends RuntimeException{
    public MemberLoginException(String message) {
        super(message);
    }
}
