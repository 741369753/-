package com.cyj.dao;

import com.cyj.entity.Goods;

import java.util.List;

public interface GoodsDao {
    int deleteByPrimaryKey(String goodId);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(String goodId);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    List<Goods> queryAll();
}