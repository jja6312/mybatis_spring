package com.ssafy.mvc.model.dto.member;

import com.ssafy.mvc.model.exception.member.MemberLoginException;

public class Member {
    private int id;
    private String email;
    private String password;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        // 이메일 유효성 검사: null, 빈 문자열, 특정 도메인 금지, 이메일 형식
        if (email == null || email.trim().isEmpty()) throw new MemberLoginException("유효하지 않은 이메일입니다. 이메일이 비어있습니다.");
        if (!email.contains("@") || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) throw new MemberLoginException("유효하지 않은 이메일 형식입니다.");
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        // 패스워드 유효성 검사: null, 빈 문자열, 최소 길이, 숫자 포함 여부, 특수 문자 포함 여부
        if (password == null || password.trim().isEmpty()) throw new MemberLoginException("패스워드가 비어있습니다.");
        if (password.length() < 8) throw new MemberLoginException("패스워드는 최소 8자 이상이어야 합니다.");
        if (!password.matches(".*\\d.*")) throw new MemberLoginException("패스워드에는 최소 하나의 숫자가 포함되어야 합니다."); // 숫자 포함 여부
        if (!password.matches(".*[!@#$%^&*].*")) throw new MemberLoginException("패스워드에는 최소 하나의 특수문자가 포함되어야 합니다."); // 특수 문자 포함 여부
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        // 이름 유효성 검사
        if (name == null || name.trim().isEmpty()) throw new MemberLoginException("이름이 비어있습니다.");
        if (name.matches(".*\\d.*")) throw new MemberLoginException("이름에 숫자가 포함될 수 없습니다.");
        if (name.length() > 20) throw new MemberLoginException("이름은 20자 이하로 입력해야 합니다.");
        this.name = name;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
