package com.cyj.service;

import com.cyj.entity.Order;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * author:aizhishang
 * time:2020/9/19
 */
public interface OrderService {
    PageInfo queryPage(Integer currentPage, Integer pageSize, Map<String ,Object> searchParm);

    int update(Order order);
}
