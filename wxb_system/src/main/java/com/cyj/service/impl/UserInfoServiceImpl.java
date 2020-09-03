package com.cyj.service.impl;

import com.cyj.dao.UserInfoDao;
import com.cyj.entity.ResultData;
import com.cyj.entity.UserExpInfo;
import com.cyj.entity.UserInfo;
import com.cyj.entity.UserRole;
import com.cyj.service.UserExpInfoService;
import com.cyj.service.UserInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.*;

/**
 * author:aizhishang
 * time:2020/8/25
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private UserExpInfoService userExpInfoService;

    @Override
    public UserInfo login(String username, String password) {
        UserInfo userInfo = userInfoDao.queryByUsernameAndPassword(username, password);
        if (userInfo != null) {
            userInfo.setLoginTime(new Date());
            updateById(userInfo);

        }
        return userInfo;
    }

    @Override
    public int updateById(UserInfo userInfo) {
        return userInfoDao.updateUserInfo(userInfo);
    }

    @Override
    public Map<String, Object> queryAll(Integer pageNum, Integer pageSize) {
        Map<String, Object> resultData = new HashMap<>();
        List<UserInfo> userInfos = null;
        try {
            PageHelper.startPage(pageNum, pageSize);
            userInfos = userInfoDao.queryAll();
            PageInfo pageInfo = new PageInfo(userInfos);
            resultData.put("code", 0);
            resultData.put("count", pageInfo.getTotal());
            resultData.put("data", pageInfo.getList());
        } catch (Exception e) {
            e.printStackTrace();
            resultData.put("code", 1);
            resultData.put("msg", e.getStackTrace().toString());
        }
        return resultData;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int updateUserAndExpAndFun(UserInfo userInfo) {
        int count1 = 1;
        if (userInfo.getUserExpInfo() != null) {
            count1 = userExpInfoService.update(userInfo.getUserExpInfo());
        }
        int count2 = 1;
        if (userInfo != null) {
            count2 = userInfoDao.updateUserInfo(userInfo);

        }

        int count3 = 1;
        if (userInfo.getRole() != null) {
            UserRole userRole = new UserRole();
            userRole.setUserId(userInfo.getUserId());
            userRole.setRoleId(userInfo.getRole().getRoleCode());
            if (userInfoDao.updateUserRole(userRole) == 0) {
                count3 = userInfoDao.insertUserRole(userRole);
            }
        }

        if (count1 > 0 && count2 > 0 && count3 > 0) {
            return 1;
        } else {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
    }

    @Override
    public ResultData insertUser(UserInfo userInfo) {
        ResultData resultData = null;
        try {
            String userId = UUID.randomUUID().toString().replace("-", "");
            userInfo.setUserId(userId);
            UserExpInfo userExpInfo = userInfo.getUserExpInfo();
            userExpInfo.setUserId(userId);
            userInfo.setUserExpInfo(userExpInfo);
            int count1 = userExpInfoService.insertInfo(userInfo.getUserExpInfo());
            int count2 = userInfoDao.insertUser(userInfo);
            UserRole userRole = new UserRole();
            userRole.setUserId(userInfo.getUserId());
            userRole.setRoleId(userInfo.getRole().getRoleCode());
            int count3 = userInfoDao.insertUserRole(userRole);

            if (count1 > 0 && count2 > 0 && count3 > 0) {
                resultData = ResultData.createSuccessResult(null);
            } else {
                resultData = ResultData.createFailResult(10006, "受影响行数为0");
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultData = ResultData.createFailResult(10007, "出现异常");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return resultData;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int deleteUser(String userId) {
        int count1 = userInfoDao.deleteUserInfo(userId);
        int count2 = userInfoDao.deleteUserRole(userId);
        int count3 = userExpInfoService.deleteExpInfo(userId);
        if (count1 >= 0 && count2 > 0 && count3 > 0) {
            return 1;
        }
        //TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        return 0;
    }


}
