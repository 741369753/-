package com.cyj.service.impl;

import com.cyj.dao.RoleDao;
import com.cyj.entity.CheckedCode;
import com.cyj.entity.ResultData;
import com.cyj.entity.Role;
import com.cyj.entity.TreeNodeData;
import com.cyj.service.RoleService;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * author:aizhishang
 * time:2020/8/26
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Autowired
    private RedisTemplate<String,String > redisTemplate;

    @Override
    public List<Role> queryAll() {
        return roleDao.queryByRoleId(null);
    }

    @Override
    public int addRole(Role role) {
        role.setRoleCode(UUID.randomUUID().toString().replace("-", ""));
        return roleDao.addRole(role);
    }

    @Override
    public int updateRole(Role role) {
        return roleDao.updateRole(role);
    }

    @Override
    public int deleteRole(String roleId) {
        return roleDao.deleteRole(roleId);
    }

    @Override
    public ResultData queryMenu(String roleId) {
        ResultData resultData = null;
        try {
            BoundValueOperations<String ,String > menuTree = redisTemplate.boundValueOps("wxb_all_role_menu_tree");
            String value = menuTree.get();
            List<TreeNodeData> treeNodeData;
            ObjectMapper mapper = new ObjectMapper();
            if (value==null){
                treeNodeData = roleDao.queryAllModule("0101");
                value = mapper.writeValueAsString(treeNodeData);
                menuTree.set(value);
            }else {
                JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, TreeNodeData.class);
                treeNodeData = mapper.readValue(value, javaType);
            }
            List<String> hasNodeCodes = roleDao.queryContainsModuleByRoleId(roleId);
            setChecked(treeNodeData,hasNodeCodes);
            if (treeNodeData==null){
                resultData = ResultData.createFailResult(10001,"查询错误");
                return resultData;
            }
            resultData = ResultData.createSuccessResult(treeNodeData);
        }catch (Exception e){
            e.printStackTrace();
            resultData = ResultData.createFailResult(10002,"出现异常");
        }

        return resultData;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ResultData updateRoleMenu(CheckedCode codes) {
        roleDao.deleteRoleFunByRoleId(codes.getRoleId());
        if (codes.getCodes()!=null&&codes.getCodes().size()>0){
            roleDao.insertRoleRun(codes);
        }

        ResultData resultData = ResultData.createSuccessResult(null);
        return resultData;
    }

    private List<TreeNodeData> setChecked(List<TreeNodeData> treeNodeData,List<String> hasNodeCodes){
        for (TreeNodeData treeNode : treeNodeData) {
            List<TreeNodeData> children = treeNode.getChildren();

            if (children!=null&&children.size()>0) {
                setChecked(children,hasNodeCodes);
            }else {
                for (String hasNodeCode : hasNodeCodes) {
                    if (hasNodeCode.equals(treeNode.getId())){
                        treeNode.setChecked(true);
                        break;
                    }
                }
            }
        }
        return treeNodeData;
    }
}
