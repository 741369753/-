package com.cyj.dao;

import com.cyj.entity.CheckedCode;
import com.cyj.entity.Role;
import com.cyj.entity.TreeNodeData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * author:aizhishang
 * time:2020/8/26
 */
public interface RoleDao {
    List<Role> queryByRoleId(String  roleId);

    int addRole(Role role);

    int updateRole(Role role);

    int deleteRole(String  roleId);

    List<TreeNodeData> queryAllModule(String parentId);

    List<String> queryContainsModuleByRoleId(String roleId);

    int deleteRoleFunByRoleId(String roleId);

    int insertRoleRun(CheckedCode checkedCode );
}
