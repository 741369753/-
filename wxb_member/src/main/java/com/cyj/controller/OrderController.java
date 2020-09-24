package com.cyj.controller;

import com.cyj.entity.Member;
import com.cyj.entity.Order;
import com.cyj.entity.ResultCode;
import com.cyj.entity.ResultData;
import com.cyj.service.OrderService;
import com.cyj.service.socket.WebSocket;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.wxpay.sdk.WXPayUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * author:aizhishang
 * time:2020/9/21
 */
@RestController
@CrossOrigin
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService orderService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private ObjectMapper mapper;

    @RequestMapping("/initMakeOrderPage")
    public ResultData initMakeOrderPage(String goodsId){
        try {
            return ResultData.createSuccessResult(orderService.initMakePage(goodsId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultData.createFailResult(ResultCode.EXPECTION, "异常");
        }
    }

    @RequestMapping("/makeOrder")
    public ResultData makeOrder(@RequestHeader("key") String key, @RequestBody Order order) throws Exception {
        Member member = mapper.readValue(stringRedisTemplate.boundValueOps(key).get(), Member.class);

        order.setOrderId(UUID.randomUUID().toString().replace("-", ""));
        order.setUserId(member.getMemeberId());
        order.setState(1);
        order.setOrderTime(new Date());
        order.setAlipayState("0");

        Map<String, Object> map = orderService.makeOrder(order);
        map.put("orderId", order.getOrderId());

        return ResultData.createSuccessResult(map);
    }

    @RequestMapping("/wxpayResult")
    public void wxpayResult(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("------");
        ServletInputStream inputStream = request.getInputStream();
        int len;
        byte[] buf = new byte[1024];
        StringBuffer sb = new StringBuffer();
        while ((len=inputStream.read(buf))!=-1){
            sb.append( new String(buf,0,len) );
        }

        Map<String, String> map = WXPayUtil.xmlToMap(sb.toString());

        //2.从微信回调的数据中获取订单编号
        String orderId = map.get("out_trade_no");
        String returnCode = map.get("return_code");

        WebSocket.sendMsg("04748ccb99dc492d8e649491a3dbafb5","success");
        // 修改订单表，状态为“已支付”
        System.out.println("修改订单【"+orderId+"】状态为已支付");

        //3.将付款状态推送到支付页面
        WebSocket.sendMsg(orderId,"success");

        //4.响应微信平台（如果不响应，微信平台则将不断调用回调接口）
        PrintWriter out = response.getWriter();
        String reMsg = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
        out.println(reMsg);
        out.flush();
        out.close();
    }
}
