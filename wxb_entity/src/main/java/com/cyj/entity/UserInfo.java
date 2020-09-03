package com.cyj.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * author:aizhishang
 * time:2020/8/25
 */
@Data
public class UserInfo {
    private String userId;
    private String name;
    private String account;
    private String password;
    private String remark;
    private Integer enabled;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date loginTime;

    private Role role;
    private UserExpInfo userExpInfo;
}
