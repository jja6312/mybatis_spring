package com.ssafy.mvc.model.exception.member;

public class MemberCreateException extends RuntimeException{
    public MemberCreateException(String message) {
        super(message);
    }
}
