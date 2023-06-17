package com.lilystu.furns.service.impl;

import com.lilystu.furns.dao.MemberDAO;
import com.lilystu.furns.dao.impl.MemberDAOimpl;
import com.lilystu.furns.entity.Member;
import com.lilystu.furns.service.MemberService;

public class MemberServiceImpl implements MemberService {
    private MemberDAO memberDAO = new MemberDAOimpl();

    @Override
    public boolean register(Member member) {
        if (!isExistsUsername(member.getUsername())){
            if (memberDAO.saveMember(member) == 1){
                System.out.println("注册成功！");
                return true;
            }else {
                System.out.println("注册失败！");
                return false;
            }
        }else {
            System.out.println("用户已存在！");
            return false;
        }
    }

    @Override
    public boolean isExistsUsername(String username) {
        return memberDAO.queryMemberByUsername(username) != null;
    }

    @Override
    public Member login(Member member) {
        return memberDAO.queryMemberByUsernameAndPassword(member.getUsername(),member.getPassword());
    }
}
