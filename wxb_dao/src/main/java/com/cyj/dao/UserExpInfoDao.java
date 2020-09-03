package com.cyj.dao;

import com.cyj.entity.UserExpInfo;
import com.cyj.entity.UserInfo;

/**
 * author:aizhishang
 * time:2020/8/29
 */
public interface UserExpInfoDao {
    UserExpInfo queryById(String id);

    int updateById(UserExpInfo userExpInfo);

    int insert(UserExpInfo userExpInfo);

    int delete(String userId);
}
