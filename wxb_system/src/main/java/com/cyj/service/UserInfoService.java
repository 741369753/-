package com.cyj.service;

import com.cyj.entity.ResultData;
import com.cyj.entity.UserInfo;

import java.util.Map;

/**
 * author:aizhishang
 * time:2020/8/25
 */
public interface UserInfoService {
    UserInfo login(String username, String password);

    int updateById(UserInfo userInfo);

    Map<String,Object> queryAll(Integer pageNum,Integer pageSize);

    int updateUserAndExpAndFun(UserInfo userInfo);

    ResultData insertUser(UserInfo userInfo);

    int deleteUser(String userId);
}
