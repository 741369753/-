package com.cyj.entity;

import lombok.Data;

/**
 * author:aizhishang
 * time:2020/8/25
 */
@Data
public class RoleFun {
    private Integer funId;
    private String moduleId;
    private String roleId;

    private Role role;
    private Module module;
}
