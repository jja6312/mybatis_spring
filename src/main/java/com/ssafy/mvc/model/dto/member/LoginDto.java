package com.ssafy.mvc.model.dto.member;

import com.ssafy.mvc.model.exception.member.MemberLoginException;

import java.util.regex.Pattern;

public class LoginDto {
    private String email;
    private String password;

    public LoginDto(String email, String password) {
        setEmail(email);
        setPassword(password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (!isValidEmail(email)) {
            throw new MemberLoginException("유효하지 않은 이메일입니다.");
        }
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (!isValidPassword(password)) {
            throw new MemberLoginException("유효하지 않은 패스워드입니다.");
        }
        this.password = password;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        return password != null && password.length() >= 8;
    }

    @Override
    public String toString() {
        return "LoginDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
