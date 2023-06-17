package com.lilystu.furns.service;

import com.lilystu.furns.entity.Member;

public interface MemberService {
    //注册用户
    public boolean register(Member member);

    //判断用户名是否存在
    public boolean isExistsUsername(String username);

    //用户登录
    Member login(Member member);
}
