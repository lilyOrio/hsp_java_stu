package com.lilystu.springcloud.service;

import com.lilystu.springcloud.entity.Member;

public interface MemberService {
    Member queryMemberById(Long id);

    int save(Member member);
}
