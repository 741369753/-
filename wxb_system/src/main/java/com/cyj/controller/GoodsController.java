package com.cyj.controller;

import com.cyj.entity.Goods;
import com.cyj.entity.ResultData;
import com.cyj.service.GoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * author:aizhishang
 * time:2020/8/30
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/getgoodslist")
    @ResponseBody
    public Map<String ,Object> getGoodsList(@RequestParam(value = "page",defaultValue = "1",required = false)Integer currentPage,@RequestParam(value = "limit",defaultValue = "10",required = false)Integer pageSize){
        PageHelper.startPage(currentPage,pageSize);

        Map<String ,Object> resultMap = new HashMap<>();

        try {
            PageInfo pageInfo = new PageInfo(goodsService.queryAll());
            resultMap.put("code", 0);
            resultMap.put("data", pageInfo.getList());
            resultMap.put("count", pageInfo.getTotal());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultMap;
    }

    @RequestMapping("/updateState")
    @ResponseBody
    public ResultData updateState(@RequestParam("goodId")String goodId, @RequestParam("state")Integer state){
        ResultData resultData = null;

        Goods goods = null;
        try {
            goods = new Goods();
            goods.setGoodId(goodId);
            goods.setState(state);
            if (goodsService.updateGoods(goods)>0){
                resultData = ResultData.createSuccessResult(null);
            }else {
                resultData = ResultData.createFailResult(10006, "受影响行数为0");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultData = ResultData.createFailResult(10007, "出现异常");
        }

        return resultData;
    }

    @RequestMapping("/changeToped")
    @ResponseBody
    public ResultData changeToped(@RequestParam("goodId")String goodId, @RequestParam("state")Integer state){
        ResultData resultData = null;

        Goods goods = null;
        try {
            goods = new Goods();
            goods.setGoodId(goodId);
            goods.setToped(state);
            if (state==1){
                goods.setTopedTime(new Date());
            }
            if (goodsService.updateGoods(goods)>0){
                resultData = ResultData.createSuccessResult(null);
            }else {
                resultData = ResultData.createFailResult(10006, "受影响行数为0");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultData = ResultData.createFailResult(10007, "出现异常");
        }

        return resultData;
    }

    @RequestMapping("/changeRecomed")
    @ResponseBody
    public ResultData changeRecomed(@RequestParam("goodId")String goodId, @RequestParam("state")Integer state){
        ResultData resultData = null;

        Goods goods = null;
        try {
            goods = new Goods();
            goods.setGoodId(goodId);
            goods.setRecomed(state);
            if (state==1){
                goods.setRecomedTime(new Date());
            }
            if (goodsService.updateGoods(goods)>0){
                resultData = ResultData.createSuccessResult(null);
            }else {
                resultData = ResultData.createFailResult(10006, "受影响行数为0");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultData = ResultData.createFailResult(10007, "出现异常");
        }

        return resultData;
    }

}
