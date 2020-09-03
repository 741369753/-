package com.cyj.service.impl;

import com.cyj.dao.GoodsDao;
import com.cyj.entity.Goods;
import com.cyj.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author:aizhishang
 * time:2020/8/30
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsDao goodsDao;

    @Override
    public List<Goods> queryAll() {
        return goodsDao.queryAll();
    }

    @Override
    public int updateGoods(Goods goods) {
        return goodsDao.updateByPrimaryKeySelective(goods);
    }
}
