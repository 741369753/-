package com.cyj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Member {
    private String memeberId;

    private String account;

    private String qqNum;

    private String email;

    private String phone;

    private String recomUser;

    private Date registerTime;

    private String payAccount;

    private String name;

    private String password;

    private String visitCode;

    private Integer useRecom;

    private String levelCode;

    private Integer mid;

    private Date updateTime;

}