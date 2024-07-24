package com.lilystu.springcloud;

import com.lilystu.springcloud.dao.MemberDao;
import com.lilystu.springcloud.entity.Member;
import com.lilystu.springcloud.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
public class MemberApplication10000Test {

    @Resource
    MemberDao memberDao;

    @Resource
    MemberService memberService;

    @Test
    public void queryMember(){
        Member member = memberDao.queryMemberById(1L);
        log.info("member{}",member);
    }

    @Test
    public void save(){
        Member member = new Member(null,"hh","123","13000000","@qq.com",1);
        memberDao.save(member);
    }

    @Test
    public void queryMemberS(){
        Member member = memberService.queryMemberById(2L);
        log.info("member~~~{}",member);
    }

    @Test
    public void saveS(){
        Member member = new Member(null,"hhhh","123","13000000","@qq.com",1);
        memberService.save(member);
    }
}
