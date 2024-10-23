package com.ssafy.mvc.model.dto.member;

public class MemberSecuInfo {
    private int id;
    private String salt;
    private String key;
    private int memberId; // foreign key

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "MemberSecuInfo{" +
                "id=" + id +
                ", salt='" + salt + '\'' +
                ", key='" + key + '\'' +
                ", memberId=" + memberId +
                '}';
    }
}
