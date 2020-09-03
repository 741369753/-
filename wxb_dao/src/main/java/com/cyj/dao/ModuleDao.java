package com.cyj.dao;

import com.cyj.entity.Module;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * author:aizhishang
 * time:2020/8/25
 */
public interface ModuleDao {
    /**
     * 通过角色id和菜单父节点递归查询菜单
     * @param roleId  角色id
     * @param parentId 父菜单id
     * @return
     */
    List<Module> queryModuleByRoleId(@Param("roleId") String roleId,@Param("parentId") String parentId);
}
