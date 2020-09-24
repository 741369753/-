package com.cyj.service;

import com.cyj.entity.Order;

import java.util.Map;

/**
 * author:aizhishang
 * time:2020/9/21
 */
public interface OrderService {
    Map<String, Object> initMakePage(String goodsId);

    Map<String, Object> makeOrder(Order order) throws Exception;
}
