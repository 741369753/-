package com.cyj.entity;

/**
 * author:aizhishang
 * time:2020/9/14
 */
public interface ResultCode {
    public final static Integer CUSTOMER_LOINFAIL=10010;//商户登录失败

    public final static Integer EXPECTION = 10006;//异常

    public final static Integer NO_LOGIN = 10011;//未登录

    public final static Integer NO_EFFECT = 10012;//无受影响行数

    public final static Integer NONE_ARGS = 10013;//参数为Null

    public final static Integer DUPLICATEKEY = 10014;//重复主键
}
