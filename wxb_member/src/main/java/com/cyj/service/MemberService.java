package com.cyj.service;

import com.cyj.entity.Member;

/**
 * author:aizhishang
 * time:2020/9/18
 */
public interface MemberService {
    Member login(String username,String password);

    int register(Member member);
}
