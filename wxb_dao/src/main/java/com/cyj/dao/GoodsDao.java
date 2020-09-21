package com.cyj.dao;

import com.cyj.entity.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsDao {
    int deleteByPrimaryKey(String goodId);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(String goodId);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    List<Goods> queryAll();

    List<Goods> queryByCustomerLikeName(@Param("customerId") String customerId,@Param("name") String name);

    List<Goods> queryByIdLikeName(@Param("goodId") String goodId,@Param("goodName") String goodName);
}