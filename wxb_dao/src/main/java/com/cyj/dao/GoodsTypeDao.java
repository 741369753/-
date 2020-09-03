package com.cyj.dao;

import com.cyj.entity.GoodsType;

public interface GoodsTypeDao {
    int deleteByPrimaryKey(String typeId);

    int insert(GoodsType record);

    int insertSelective(GoodsType record);

    GoodsType selectByPrimaryKey(String typeId);

    int updateByPrimaryKeySelective(GoodsType record);

    int updateByPrimaryKey(GoodsType record);
}