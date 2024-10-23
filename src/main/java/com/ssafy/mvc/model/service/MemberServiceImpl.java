package com.ssafy.mvc.model.service;

import com.ssafy.mvc.model.dao.MemberDao;
import com.ssafy.mvc.model.dao.MemberSecuInfoDao;
import com.ssafy.mvc.model.dto.member.LoginDto;
import com.ssafy.mvc.model.dto.member.Member;
import com.ssafy.mvc.model.dto.member.MemberSecuInfo;
import com.ssafy.mvc.model.dto.member.MemberWithoutValid;
import com.ssafy.mvc.model.exception.member.MemberLoginException;
import com.ssafy.mvc.util.OpenCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service

public class MemberServiceImpl implements MemberService{
    private final MemberDao memberDao;
    private final MemberSecuInfoDao memberSecuInfoDao;

    public MemberServiceImpl(MemberDao memberDao, MemberSecuInfoDao memberSecuInfoDao) {
        this.memberDao = memberDao;
        this.memberSecuInfoDao = memberSecuInfoDao;
    }

    //회원가입
    @Override
//    @Transactional
    public void createMember(Member member) {
        String salt = UUID.randomUUID().toString();
        String encPw = OpenCrypt.byteArrayToHex(OpenCrypt.getSHA256(member.getPassword(), salt));
        System.out.println("암호화:encPw:"+encPw);
        member.setEncryptedPassword(encPw);
        System.out.println(1);
        memberDao.insertMember(member);
        System.out.println(2);

        MemberWithoutValid memberWithoutValid = memberDao.findByEmail(member.getEmail());
        System.out.println("memberWithoutValid의 Id"+memberWithoutValid.getId());

        MemberSecuInfo memberSecuInfo = new MemberSecuInfo();
        memberSecuInfo.setMemberId(memberWithoutValid.getId());  // member의 ID를 참조
        memberSecuInfo.setSalt(salt);  // AOP에서 전달된 salt 값

        memberSecuInfoDao.insertMemberSecuInfo(memberSecuInfo);  // member_secuinfo 테이블에 저장
        System.out.println(3);
    }


    //로그인
    @Override
    public MemberWithoutValid login(Member member) {
        MemberWithoutValid findMember = memberDao.findByEmail(member.getEmail());
        if(findMember == null) throw new MemberLoginException("로그인 오류");

        MemberSecuInfo secuInfo = memberSecuInfoDao.findByMemberId(findMember.getId());

        String findMemberSalt = secuInfo.getSalt();
        String encPw = OpenCrypt.byteArrayToHex(OpenCrypt.getSHA256(member.getPassword(), findMemberSalt));

        System.out.println("secuInfo에서 찾은 Salt:"+secuInfo.getSalt());
        System.out.println("findMemberSalt:"+findMemberSalt);
        System.out.println("findMember.암호:"+findMember.getPassword());
        System.out.println("encPw:"+encPw);


        if(findMember.getPassword().equals(encPw)) return findMember;
        else return null;
    }
}
