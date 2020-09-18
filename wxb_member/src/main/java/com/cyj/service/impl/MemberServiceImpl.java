package com.cyj.service.impl;

import com.cyj.dao.MemberDao;
import com.cyj.entity.Member;
import com.cyj.service.MemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * author:aizhishang
 * time:2020/9/18
 */
@Service
public class MemberServiceImpl implements MemberService {
    @Resource
    private MemberDao memberDao;

    @Override
    public Member login(String username, String password) {
        return memberDao.login(username, password);
    }
}
