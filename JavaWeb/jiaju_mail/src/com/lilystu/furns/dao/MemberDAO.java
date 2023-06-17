package com.lilystu.furns.dao;

import com.lilystu.furns.entity.Member;

public interface MemberDAO {
    /**通过用户名返回member
     * @param userName
     * @return
     */
    public Member queryMemberByUsername(String userName);


    /**
     * 保存一个Member对象到数据库Member表,
     * @param member
     * @return 1 保存成功
     */
    public int saveMember(Member member);

    /**通过用户名和密码返回member
     * @param userName
     * @return
     */
    public Member queryMemberByUsernameAndPassword(String userName,String password);

}
