package com.cyj.service.impl;

import com.cyj.dao.UserExpInfoDao;
import com.cyj.entity.UserExpInfo;
import com.cyj.service.UserExpInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author:aizhishang
 * time:2020/8/29
 */
@Service
public class UserExpInfoServiceImpl implements UserExpInfoService {
    @Autowired
    private UserExpInfoDao userExpInfoDao;

    @Override
    public UserExpInfo queryById(String id) {
        return userExpInfoDao.queryById(id);
    }

    @Override
    public int update(UserExpInfo userExpInfo) {
        return userExpInfoDao.updateById(userExpInfo);
    }

    @Override
    public int insertInfo(UserExpInfo userExpInfo) {
        return userExpInfoDao.insert(userExpInfo);
    }

    @Override
    public int deleteExpInfo(String userId) {
        return userExpInfoDao.delete(userId);
    }
}
