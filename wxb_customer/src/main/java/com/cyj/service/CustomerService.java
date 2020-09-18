package com.cyj.service;

import com.cyj.entity.Customer;

/**
 * author:aizhishang
 * time:2020/9/14
 */
public interface CustomerService {
    Customer queryByLoginName(Customer customer);
}
