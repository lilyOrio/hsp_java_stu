package com.lilystu.furns.test;

import com.lilystu.furns.entity.Member;
import com.lilystu.furns.service.MemberService;
import com.lilystu.furns.service.impl.MemberServiceImpl;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
    private MemberService memberService = new MemberServiceImpl();
    @Test
    public void isExistsUsername(){
        boolean admin = memberService.isExistsUsername("admin");
        if (admin){
            System.out.println("已存在！");
        }else {
            System.out.println("不存在！");
        }
    }

    @Test
    public void register(){
        boolean register = memberService.register(new Member(null, "king", "king",
                "king@qq.com"));
        if (register){
            System.out.println("注册成功！");
        }else {
            System.out.println("注册失败！");
        }
    }

    @Test
    public void login(){
        Member login = memberService.login(new Member(null, "king", "king", null));
        System.out.println("member = " + login);
    }
}
