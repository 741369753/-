package com.cyj.dao;

import com.cyj.entity.UserInfo;
import com.cyj.entity.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * author:aizhishang
 * time:2020/8/25
 */
public interface UserInfoDao {

    /**
     * 通过用户名密码查询用户
     * @param username
     * @param password
     * @return
     */
    UserInfo queryByUsernameAndPassword(@Param("username") String username,@Param("password") String password);

    /**
     * 查询所有用户
     * @return
     */
    List<UserInfo> queryAll();

    /**
     * 修改用户信息
     * @param userInfo
     * @return
     */
    int updateUserInfo(UserInfo userInfo);

    int updateUserRole(UserRole userRole);

    int insertUser(UserInfo userInfo);

    int insertUserRole(UserRole userRole);

    int deleteUserInfo(String userId);

    int deleteUserRole(String userId);

}
