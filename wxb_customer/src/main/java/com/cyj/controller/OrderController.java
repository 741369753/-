package com.cyj.controller;

import com.cyj.entity.Order;
import com.cyj.entity.ResultCode;
import com.cyj.entity.ResultData;
import com.cyj.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * author:aizhishang
 * time:2020/9/19
 */
@RestController
@CrossOrigin
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService orderService;

    /**
     * @param currentPage
     * @param pageSize
     * @param goodsName
     * @param orderId
     * @param buyerName
     * @param buyerPhone
     * @param userAccount
     * @param state
     * @param beginTimeStr 前端传入格式 2020-02-20T20:20 使用Date类型接收不到 @DateTimeFormat不知如何设置该接收格式
     * @param endTimeStr
     * @return
     */
    @RequestMapping("/getOrders")
    public ResultData getOrders(@RequestParam(value = "currentPage", defaultValue = "1", required = false) Integer currentPage, @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,  String goodsName, String orderId, String buyerName, String buyerPhone, String userAccount, Integer state, @RequestParam(value = "beginTime", required = false) String beginTimeStr, @RequestParam(value = "endTime", required = false) String endTimeStr) {
        try {
            System.out.println(goodsName + " " + orderId + " " + buyerName + " " + buyerPhone + " " + userAccount + " " + beginTimeStr + " " + endTimeStr);
            //搜索参数
            Map<String, Object> map = new HashMap<>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss");
            if (beginTimeStr != null) {
                //前端使用input type为datetime-local作为日期控件，传入的格式为 2020-02-20T20:20 (日期与时间中间有T)，替换成常用格式
                beginTimeStr = beginTimeStr.replace("T", " ");
                Date beginTime = sdf.parse(beginTimeStr);
                map.put("beginTime", beginTime);
            }
            if (endTimeStr != null) {
                endTimeStr = endTimeStr.replace("T", " ");
                Date endTime = sdf.parse(endTimeStr);
                map.put("endTime", endTime);
            }

            map.put("goodsName", likeParam(goodsName));
            map.put("orderId", likeParam(orderId));
            map.put("buyerName", likeParam(buyerName));
            map.put("buyerPhone", likeParam(buyerPhone));
            map.put("userAccount", likeParam(userAccount));
            map.put("state", state);

            return ResultData.createSuccessResult(orderService.queryPage(currentPage, pageSize, map));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultData.createFailResult(ResultCode.EXPECTION, "异常");
        }
    }

    @RequestMapping("/changeState")
    public ResultData changeState(@RequestBody Order order) {
        ResultData resultData = null;
        try {
            if (orderService.update(order) > 0) {
                resultData= ResultData.createSuccessResult(order);
            }else {
                resultData = ResultData.createFailResult(ResultCode.NO_EFFECT, "受影响行数0");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultData = ResultData.createFailResult(ResultCode.EXPECTION, "异常");
        }
        return resultData;
    }

    /**
     * 模糊查询 字符串加 %%
     * @param str
     * @return
     */
    private String likeParam(String str) {
        //字符串为null或空不能处理，否则sql查询不正确.
        if (str != null && !str.isEmpty()) {
            return "%" + str + "%";
        }
        return str;
    }
}
