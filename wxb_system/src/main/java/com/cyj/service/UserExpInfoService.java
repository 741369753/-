package com.cyj.service;

import com.cyj.entity.UserExpInfo;

/**
 * author:aizhishang
 * time:2020/8/29
 */
public interface UserExpInfoService {
    UserExpInfo queryById(String id);

    int update(UserExpInfo userExpInfo);

    int insertInfo(UserExpInfo userExpInfo);

    int deleteExpInfo(String userId);
}
