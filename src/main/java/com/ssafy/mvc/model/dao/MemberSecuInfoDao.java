package com.ssafy.mvc.model.dao;

import com.ssafy.mvc.model.dto.member.MemberSecuInfo;

public interface MemberSecuInfoDao {
    void insertMemberSecuInfo(MemberSecuInfo memberSecuInfo);

    MemberSecuInfo findByMemberId(int id);
}
