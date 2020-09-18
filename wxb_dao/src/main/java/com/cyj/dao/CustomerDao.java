package com.cyj.dao;

import com.cyj.entity.Customer;

import java.util.List;

public interface CustomerDao {
    int deleteByPrimaryKey(String customerId);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(String customerId);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);

    List<Customer> queryAll();

    Customer queryByLoginName(String loginName);
}