package com.lilystu.furns.test;

import com.lilystu.furns.dao.MemberDAO;
import com.lilystu.furns.dao.impl.MemberDAOimpl;
import com.lilystu.furns.entity.Member;
import org.junit.jupiter.api.Test;

public class MemberDAOTest {
    private MemberDAO memberDAO = new MemberDAOimpl();

    @Test
    public void queryMemberByUsername(){
        Member admin = memberDAO.queryMemberByUsername("admin");
        if (admin == null){
            System.out.println("用户不存在！");
        }else {
            System.out.println("找到用户 Member= " + admin);
        }
    }

    @Test
    public void saveMember(){
        int b = memberDAO.saveMember(new Member(null, "lilili",
                "123456", "123@qq.com"));
        if (b != -1){
            System.out.println("保存成功！");
        }else {
            System.out.println("保存失败！");
        }
    }

    @Test
    public void queryMemberByUsernameAndPassword(){
        Member member = memberDAO.queryMemberByUsernameAndPassword("lilili", "123456");
        System.out.println("Member = " + member);
    }
}
