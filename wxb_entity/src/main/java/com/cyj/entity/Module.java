package com.cyj.entity;

import lombok.Data;

import java.util.List;

/**
 * author:aizhishang
 * time:2020/8/25
 */
@Data
public class Module {
    private String moduleCode;
    private String moduleName;
    private String linkUrl;
    private Integer moduleOrder;
    private String parentModule;
    private String moduleDesc;
    private String expanded;
    private String leaf;

    private Role role;
    private List<Module> childModules;
}
