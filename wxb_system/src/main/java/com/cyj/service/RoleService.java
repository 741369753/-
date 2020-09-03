package com.cyj.service;

import com.cyj.entity.CheckedCode;
import com.cyj.entity.ResultData;
import com.cyj.entity.Role;

import java.util.List;

/**
 * author:aizhishang
 * time:2020/8/26
 */
public interface RoleService {
    List<Role> queryAll();

    int addRole(Role role);

    int updateRole(Role role);

    int deleteRole(String roleId);

    ResultData queryMenu(String roleId);

    ResultData updateRoleMenu(CheckedCode codes);
}
