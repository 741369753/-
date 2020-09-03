package com.cyj.entity;

import lombok.Data;

/**
 * author:aizhishang
 * time:2020/8/25
 */
@Data
public class UserRole {
    private Integer id;
    private String userId;
    private String roleId;

    private UserInfo userInfo;
    private Role role;
}
