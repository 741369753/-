package com.cyj.service;

import com.cyj.entity.Module;

import java.util.List;

/**
 * author:aizhishang
 * time:2020/8/26
 */
public interface ModuleService {
    List<Module> queryMenu(String roleId);
}
