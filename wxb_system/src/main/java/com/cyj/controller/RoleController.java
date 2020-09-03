package com.cyj.controller;

import com.cyj.entity.CheckedCode;
import com.cyj.entity.ResultData;
import com.cyj.entity.Role;
import com.cyj.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * author:aizhishang
 * time:2020/8/26
 */
@Controller
@RequestMapping("role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("/role_list")
    public String RolePage(Model model){
        List<Role> roles = roleService.queryAll();
        model.addAttribute("roleList", roles);
        return "property/role_list";
    }

    @RequestMapping("/rolelistjson")
    @ResponseBody
    public ResultData getAllRole(){
        ResultData resultData = null;
        try {
        List<Role> roles = roleService.queryAll();
            resultData = ResultData.createSuccessResult(roles);
        }catch (Exception e){
            e.printStackTrace();
            resultData  = ResultData.createFailResult(10010, "出现异常");
        }
        return resultData;
    }

    @RequestMapping("add")
    public String addRole(Role role){
        roleService.addRole(role);

        return "redirect:/role/role_list";
    }

    @RequestMapping("update")
    public String updateRole(Role role){
        roleService.updateRole(role);

        return "redirect:/role/role_list";
    }

    @RequestMapping("delete")
    public String deleteRole(String roleId){
        roleService.deleteRole(roleId);

        return "redirect:/role/role_list";
    }

    @RequestMapping("/roleTreeNodeListPage")
    public String treeNodeListPage(){
        return "property/treeNode";
    }

    @RequestMapping("/queryMenu")
    @ResponseBody
    public ResultData queryMenu(String roleId){
        return roleService.queryMenu(roleId);
    }

    @RequestMapping("/updateMenu")
    @ResponseBody
    public ResultData updateMenu(@RequestBody CheckedCode codes){
        ResultData resultData = null;
        try {
            resultData = roleService.updateRoleMenu(codes);
        } catch (Exception e) {
            e.printStackTrace();
            resultData = ResultData.createFailResult(10002, "出现异常");
        }

        return resultData;
    }
}
