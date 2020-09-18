package com.cyj.service.impl;

import com.cyj.dao.CustomerDao;
import com.cyj.entity.Customer;
import com.cyj.service.CustomerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * author:aizhishang
 * time:2020/9/14
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    @Resource
    private CustomerDao customerDao;

    @Override
    public Customer queryByLoginName(Customer customer) {
        Customer queryCustomer = customerDao.queryByLoginName(customer.getLoginName());
        if (queryCustomer!=null&&queryCustomer.getLoginPwd().equals(customer.getLoginPwd())){
            return queryCustomer;
        }
        return null;
    }
}
