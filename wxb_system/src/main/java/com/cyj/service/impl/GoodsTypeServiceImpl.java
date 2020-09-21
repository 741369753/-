package com.cyj.service.impl;

import com.cyj.dao.GoodsTypeDao;
import com.cyj.entity.GoodsType;
import com.cyj.service.GoodsTypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * author:aizhishang
 * time:2020/9/18
 */
@Service
public class GoodsTypeServiceImpl implements GoodsTypeService {
    @Resource
    private GoodsTypeDao goodsTypeDao;

    @Override
    public PageInfo queryType(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);

        PageInfo pageInfo = new PageInfo(goodsTypeDao.queryAll());
        return pageInfo;
    }

    @Override
    public int updateType(GoodsType goodsType) {
        return goodsTypeDao.updateByPrimaryKeySelective(goodsType);
    }

    @Override
    public int insertType(GoodsType goodsType) {
        return goodsTypeDao.insertSelective(goodsType);
    }

    @Override
    public int deleteType(String typeId) {
        return goodsTypeDao.deleteByPrimaryKey(typeId);
    }
}
