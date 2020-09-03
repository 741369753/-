package com.cyj.entity;

import lombok.Data;

/**
 * author:aizhishang
 * time:2020/8/25
 */
@Data
public class Role {
    private String  roleCode;
    private String roleName;
    private String roleDesc;
    private Integer roleOrder;
    private Integer roleType;
}
