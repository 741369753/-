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

    /**
     * 初始化页面数据，返回商品类型与文案信息（微信文案）
     * @param customer
     * @return
     */
    Map<String, Object> addPageInit(Customer customer);


    String upload(MultipartFile imgFile);

    int addGoods(Goods goods);

    /**
     * 删除商品
     * @param goodsId
     * @return -1表示传入id为null或空字符串;0表示受影响行数为0;1表示成功
     */
    int deleteGoods(String goodsId);
}
