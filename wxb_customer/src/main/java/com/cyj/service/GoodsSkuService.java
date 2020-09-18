package com.cyj.service;

import com.cyj.entity.GoodsSku;

import java.util.List;

/**
 * author:aizhishang
 * time:2020/9/17
 */
public interface GoodsSkuService {
    int insertSkus(List<GoodsSku> goodsSkus);

    /**
     * 集合GoodsSku对象生成id并将goodsId绑定到对象中
     * @param goodsSkus
     * @param goodsId
     * @return
     */
    List<GoodsSku> initSkuList(List<GoodsSku> goodsSkus,String goodsId);
}
