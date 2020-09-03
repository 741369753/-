package com.cyj.service.impl;

import com.cyj.dao.CustomerDao;
import com.cyj.entity.Customer;
import com.cyj.service.CustomerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author:aizhishang
 * time:2020/8/28
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerDao customerDao;

    @Override
    public Map<String, Object> queryAll(Integer pageNum,Integer pageSize) {
        Map<String ,Object> resultData = new HashMap<>();
        List<Customer> customers = null;
        try {
            PageHelper.startPage(pageNum,pageSize);
            customers = customerDao.queryAll();
            PageInfo pageInfo = new PageInfo(customers);
            resultData.put("code", 0);
            resultData.put("count",pageInfo.getTotal());
            resultData.put("data", pageInfo.getList());
        } catch (Exception e) {
            e.printStackTrace();
            resultData.put("code", 1);
            resultData.put("msg", e.getStackTrace().toString());
        }
        return resultData;
    }

    @Override
    public int deleteById(String  id) {
       return customerDao.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Customer customer) {
        return customerDao.updateByPrimaryKeySelective(customer);
    }

    @Override
    public int inser(Customer customer) {

        return customerDao.insertSelective(customer);
    }
}
