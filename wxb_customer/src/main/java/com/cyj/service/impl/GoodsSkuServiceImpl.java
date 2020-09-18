package com.cyj.service.impl;

import com.cyj.dao.GoodsSkuDao;
import com.cyj.entity.GoodsSku;
import com.cyj.service.GoodsSkuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

/**
 * author:aizhishang
 * time:2020/9/17
 */
@Service
public class GoodsSkuServiceImpl implements GoodsSkuService {
    @Resource
    private GoodsSkuDao goodsSkuDao;
    @Override
    public int insertSkus(List<GoodsSku> goodsSkus) {
        if(goodsSkus==null||goodsSkus.size()==0){
            return -1;
        }

        return goodsSkuDao.insertList(goodsSkus);
    }

    @Override
    public List<GoodsSku> initSkuList(List<GoodsSku> goodsSkus,String goodsId){
        String id=null;
        for (GoodsSku skus : goodsSkus) {
            id = String.format("%08d", new Random().nextInt(100000000));
            skus.setSkuId(id);
            skus.setGoodId(goodsId);
        }
        return goodsSkus;
    }
}
