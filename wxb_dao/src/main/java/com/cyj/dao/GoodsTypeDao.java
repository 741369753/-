package com.cyj.dao;

import com.cyj.entity.GoodsType;

import java.util.List;

public interface GoodsTypeDao {
    int deleteByPrimaryKey(String typeId);

    int insert(GoodsType record);

    int insertSelective(GoodsType record);

    GoodsType selectByPrimaryKey(String typeId);

    int updateByPrimaryKeySelective(GoodsType record);

    int updateByPrimaryKey(GoodsType record);

    List<GoodsType> queryAll();
}