package com.lilystu.springcloud.service.impl;

import com.lilystu.springcloud.dao.MemberDao;
import com.lilystu.springcloud.entity.Member;
import com.lilystu.springcloud.service.MemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MemberServiceImpl implements MemberService {
    @Resource
    MemberDao memberDao;

    @Override
    public Member queryMemberById(Long id) {
        return memberDao.queryMemberById(id);
    }

    @Override
    public int save(Member member) {
        return memberDao.save(member);
    }
}
