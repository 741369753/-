package com.cyj.service.impl;

import com.cyj.dao.GoodsTypeDao;
import com.cyj.entity.GoodsType;
import com.cyj.service.GoodsAndCopyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author:aizhishang
 * time:2020/9/20
 */
@Service
public class GoodsAndCopyServiceImpl implements GoodsAndCopyService {
    @Resource
    private GoodsTypeDao goodsTypeDao;
    @Override
    public Map<String, Object> init() {
        Map<String ,Object> map = new HashMap();
        List<GoodsType> goodsTypes = goodsTypeDao.queryAll();
        map.put("goodsTypes", goodsTypes);
        return map;
    }
}
