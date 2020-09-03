package com.cyj.service.impl;

import com.cyj.dao.ModuleDao;
import com.cyj.entity.Module;
import com.cyj.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author:aizhishang
 * time:2020/8/26
 */
@Service
public class ModuleServiceImpl implements ModuleService {
    @Autowired
    private ModuleDao moduleDao;

    @Override
    public List<Module> queryMenu(String roleId) {
        return  moduleDao.queryModuleByRoleId(roleId, "0101");
    }
}
