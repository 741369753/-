package com.cyj.service;

import com.cyj.entity.Goods;

import java.util.List;

/**
 * author:aizhishang
 * time:2020/8/30
 */
public interface GoodsService {
    List<Goods> queryAll();

    int updateGoods(Goods goods);
}
