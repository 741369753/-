package com.cyj.service;

import com.cyj.entity.Customer;

import java.util.Map;

/**
 * author:aizhishang
 * time:2020/8/28
 */
public interface CustomerService {
    Map<String, Object> queryAll(Integer pageNum,Integer pageSize);

    int deleteById(String id);

    int update(Customer customer);

    int inser(Customer customer);
}
