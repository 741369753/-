package com.cyj.service;

import com.cyj.entity.GoodsCopy;
import com.github.pagehelper.PageInfo;

/**
 * author:aizhishang
 * time:2020/9/17
 */
public interface GoodsCopyService {
    PageInfo queryGoodsCopyPageLike(String search, String customerId, int currentPage, int pageSize);

    int updateGoodsCopy(GoodsCopy goodsCopy);

    int deleteGoodsCopy(Integer id);

    int addGoodsCopy(GoodsCopy goodsCopy);
}
