package com.cyj.service.impl;

import com.cyj.dao.MemberDao;
import com.cyj.entity.Member;
import com.cyj.service.MemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;

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

    @Override
    public int register(Member member) {
        member.setMemeberId(new Random().nextInt(10)+String.format("%08d", new Random().nextInt(1000000000)));
        return memberDao.insertSelective(member);
    }
}
