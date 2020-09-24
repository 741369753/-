package com.cyj.service.impl;

import com.cyj.config.MywxConfig;
import com.cyj.dao.*;
import com.cyj.entity.Order;
import com.cyj.service.OrderService;
import com.github.wxpay.sdk.WXPay;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * author:aizhishang
 * time:2020/9/21
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;
    @Resource
    private GoodsDao goodsDao;
    @Resource
    private GoodsSkuDao goodsSkuDao;
    @Resource
    private ProvinceDao provinceDao;
    @Resource
    private SysCodeDao sysCodeDao;

    @Override
    public Map<String, Object> initMakePage(String goodsId) {
        Map<String ,Object> map = new HashMap<>();

        map.put("goods",goodsDao.selectByPrimaryKey(goodsId));
        map.put("goodsSkus", goodsSkuDao.queryByGoodsId(goodsId));
        map.put("provinces",provinceDao.queryAll());
        map.put("orderTypes",sysCodeDao.queryByFieldCode("ORDER_TYPE"));

        return map;
    }

    @Override
    public Map<String, Object> makeOrder(Order order) throws Exception {
        //1.保存订单
        orderDao.insertSelective(order);

        //2.向微信平台发起支付请求，获取支付短连接
        WXPay wxPay = new WXPay(new MywxConfig());
        Map<String, String> data = new HashMap<>();
        data.put("body", order.getGoods().getGoodName());
        data.put("out_trade_no", order.getOrderId());
        data.put("device_info", "");
        data.put("fee_type", "CNY");
        data.put("total_fee", "1");
        data.put("spbill_create_ip", "123.12.12.123");
        //付款状态的回调接口
        data.put("notify_url", "http://aizhishang.free.idcfengye.com/wxb_member/order/wxpayResult");
        data.put("trade_type", "NATIVE");  // 此处指定为扫码支付
        data.put("product_id", order.getGoodId());

        //发送支付请求，resp就是微信支付平台的返回的数据（包含支付链接）
        Map<String, String> resp = wxPay.unifiedOrder(data);

        Map<String ,Object> map = new HashMap<>();

        String payUrl = resp.get("code_url");

        map.put("payUrl", payUrl);
        //3. 将支付短连接传递到前端
        return map;
    }
}
