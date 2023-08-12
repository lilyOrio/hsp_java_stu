package com.lily_stu.spring.service;

import com.lily_stu.spring.DAO.MemberDAOImpl;

public class MemberServiceImpl {
    private MemberDAOImpl memberDAO;

    public MemberDAOImpl getMemberDAO() {
        return memberDAO;
    }

    public void setMemberDAO(MemberDAOImpl memberDAO) {
        this.memberDAO = memberDAO;
    }

    public void add(){
        System.out.println("MemberServiceImpl的add()方法被调用。。。");
        memberDAO.add();
    }
}
