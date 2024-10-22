package com.ssafy.mvc.model.service;

import com.ssafy.mvc.model.dao.MemberDao;
import com.ssafy.mvc.model.dto.member.LoginDto;
import com.ssafy.mvc.model.dto.member.Member;
import com.ssafy.mvc.util.OpenCrypt;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{
    private final MemberDao memberDao;

    public MemberServiceImpl(MemberDao memberDao) {
        this.memberDao = memberDao;
    }


    //회원가입
    @Override
    public void createMember(Member member) {
        memberDao.createMember(member);
    }


    //로그인
    @Override
    public Member login(Member member) {
        Member findMember = memberDao.findByEmail(member.getEmail());
        System.out.println("findMember:"+findMember);

        // 사용자가 입력한 비밀번호를 해시화
        String salt = "salt";  // 솔트는 안전한 방식으로 관리되어야 함
        byte[] hashedInputPassword = OpenCrypt.getSHA256(member.getPassword(), salt);
        String hashedInputPasswordHex = OpenCrypt.byteArrayToHex(hashedInputPassword);

        if(findMember.getPassword().equals(hashedInputPasswordHex)) return findMember;
        else return null;
    }
}
