package com.cyj.dao;

import com.cyj.entity.GoodsSku;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsSkuDao {
    int deleteByPrimaryKey(String skuId);

    int insert(GoodsSku record);

    int insertSelective(GoodsSku record);

    GoodsSku selectByPrimaryKey(String skuId);

    int updateByPrimaryKeySelective(GoodsSku record);

    int updateByPrimaryKey(GoodsSku record);

    List<GoodsSku> queryByGoodsId(String goodsId);

    int insertList(@Param("goodsSkus") List<GoodsSku> goodsSkus);

    int deleteByGoodsId(String goodsId);
}