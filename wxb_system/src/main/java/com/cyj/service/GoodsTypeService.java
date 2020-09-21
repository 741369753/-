package com.cyj.service;

import com.cyj.entity.GoodsType;
import com.github.pagehelper.PageInfo;

/**
 * author:aizhishang
 * time:2020/9/18
 */
public interface GoodsTypeService {
    PageInfo queryType(Integer page, Integer limit);

    int updateType(GoodsType goodsType);

    int insertType(GoodsType goodsType);

    int deleteType(String typeId);
}
