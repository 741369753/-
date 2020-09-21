package com.cyj.service.impl;

import com.cyj.dao.GoodsCopyDao;
import com.cyj.entity.GoodsCopy;
import com.cyj.service.GoodsCopyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * author:aizhishang
 * time:2020/9/17
 */
@Service
public class GoodsCopyServiceImpl implements GoodsCopyService {
    @Resource
    private GoodsCopyDao goodsCopyDao;

    @Override
    public PageInfo queryGoodsCopyPageLike(String search, String customerId, int currentPage, int pageSize) {
        //生成PageHelper分页
        PageHelper.startPage(currentPage,pageSize);

        if (search!=null&&!search.isEmpty()){
            search="%"+search+"%";
        }
        //获取分页数据
        PageInfo pageInfo = new PageInfo(goodsCopyDao.queryByCustomerIdPageLike(search,customerId));

        return pageInfo;
    }

    @Override
    public int updateGoodsCopy(GoodsCopy goodsCopy) {
        return goodsCopyDao.updateByPrimaryKeySelective(goodsCopy);
    }

    @Override
    public int deleteGoodsCopy(Integer id) {
        return goodsCopyDao.deleteByPrimaryKey(id);
    }

    @Override
    public int addGoodsCopy(GoodsCopy goodsCopy) {
        return goodsCopyDao.insertSelective(goodsCopy);
    }
}
