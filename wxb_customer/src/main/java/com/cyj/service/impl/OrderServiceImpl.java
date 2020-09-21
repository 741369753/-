package com.cyj.service.impl;

import com.cyj.dao.OrderDao;
import com.cyj.entity.Order;
import com.cyj.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * author:aizhishang
 * time:2020/9/19
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;

    @Override
    public PageInfo queryPage(Integer currentPage, Integer pageSize, Map<String, Object> searchParm) {
        PageHelper.startPage(currentPage,pageSize);

        PageInfo pageInfo = new PageInfo(orderDao.queryByParms(searchParm));

        return pageInfo;
    }

    @Override
    public int update(Order order) {
        return orderDao.updateByPrimaryKeySelective(order);
    }

}
