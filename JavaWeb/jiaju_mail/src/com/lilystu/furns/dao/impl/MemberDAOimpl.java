package com.lilystu.furns.dao.impl;

import com.lilystu.furns.dao.BasicDAO;
import com.lilystu.furns.dao.MemberDAO;
import com.lilystu.furns.entity.Member;

public class MemberDAOimpl extends BasicDAO<Member> implements MemberDAO {
    /**
     * 通过用户名返回对应的Member，
     * @param userName
     * @return null 表示未找到该Member
     */
    @Override
    public Member queryMemberByUsername(String userName) {
        //sql 语句建议现在sqlyog测试
        String sql = "SELECT `id`,`username`,`password`,`email` FROM `member`" +
                "WHERE `username` = ?";
        return querySingle(sql,Member.class,userName);
    }

    /**
     * 保存一个用户
     * @param member
     * @return -1 表示保存失败
     */
    @Override
    public int saveMember(Member member) {
        String sql = "INSERT INTO `member`(`username`,`password`,`email`)" +
                "VALUE (?,MD5(?),?)";
        return update(sql,member.getUsername(),member.getPassword(),member.getEmail());
    }

    /**
     * 根据用户名和密码返回一个Member对象
     * @param userName
     * @param password
     * @return null表示不存在
     */
    @Override
    public Member queryMemberByUsernameAndPassword(String userName, String password) {
        String sql = "SELECT `id`,`username`,`password`,`email` FROM `member`" +
                "WHERE `username` = ? AND `password` = MD5(?)" ;
        return querySingle(sql,Member.class,userName,password);
    }
}
