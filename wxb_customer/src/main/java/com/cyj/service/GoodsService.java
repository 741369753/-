package com.cyj.service;

import com.cyj.entity.Customer;
import com.cyj.entity.Goods;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * author:aizhishang
 * time:2020/8/30
 */
public interface GoodsService {
    List<Goods> queryByCustomerIdLikeName(String customerId,String name);

    int updateGoods(Goods goods);

    Map<String, Object> addPageInit(Customer customer);


    String upload(MultipartFile imgFile);

    int addGoods(Goods goods);

    int deleteGoods(String goodsId);
}
