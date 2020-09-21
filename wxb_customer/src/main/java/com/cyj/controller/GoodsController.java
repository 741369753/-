package com.cyj.controller;

import com.cyj.entity.*;
import com.cyj.service.GoodsService;
import com.cyj.service.GoodsSkuService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.*;

/**
 * author:aizhishang
 * time:2020/9/15
 */
@RestController
@CrossOrigin
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @Resource
    private GoodsSkuService goodsSkuService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private ObjectMapper mapper = new ObjectMapper();

    @CrossOrigin(methods = RequestMethod.GET)
    @GetMapping("/getgoodslist")
    public Map<String ,Object> getGoodsList(@RequestHeader("key") String key,@RequestParam(value = "page",defaultValue = "1",required = false)Integer currentPage, @RequestParam(value = "limit",defaultValue = "10",required = false)Integer pageSize,@RequestParam(value = "search",required = false) String searchStr){
        Map<String ,Object> resultMap = null;
        try {
            PageHelper.startPage(currentPage,pageSize);

            resultMap = new HashMap<>();

            //从redis中获取登录用户信息
            Customer customer = mapper.readValue(stringRedisTemplate.boundValueOps(key).get(), Customer.class);

            try {
                //模糊查询
                if (searchStr!=null&&!searchStr.isEmpty()){
                    searchStr="%"+searchStr+"%";
                }
                PageInfo pageInfo = new PageInfo(goodsService.queryByCustomerIdLikeName(customer.getCustomerId(),searchStr));
                resultMap.put("code", 10000);
                resultMap.put("data", pageInfo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultMap;
    }

    @RequestMapping("/addGoods")
    public ResultData addGoods(@RequestBody GoodsAdd goodsAdd,@RequestHeader("key") String key){
        try {
            //goodsAdd包含goods(商品)和goodsSku(套餐)信息
            Goods goods = goodsAdd.getGoods();
            System.out.println(goods);
            //生成商品id，当前时间毫秒数+2位随机数
            String goodsId = System.currentTimeMillis() + "" + new Random().nextInt(100);
            Customer customer = mapper.readValue(stringRedisTemplate.boundValueOps(key).get(), Customer.class);

            //生成商品必要信息
            goods.setGoodId(goodsId);
            goods.setCustomerId(customer.getCustomerId());
            goods.setCreateTime(new Date());

            //初始化套餐数据，添加必要信息
            List<GoodsSku> goodsSkus = goodsSkuService.initSkuList(goodsAdd.getGoodsSku(), goodsId);

            ResultData resultData = null;
            if (goodsService.addGoods(goods)>0&&goodsSkuService.insertSkus(goodsSkus)!=0){
                return ResultData.createSuccessResult("添加成功");
            }else {
                return ResultData.createFailResult(ResultCode.NO_EFFECT, "受影响行数为0");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResultData.createFailResult(ResultCode.EXPECTION, "异常");
    }

    @RequestMapping("/addPageInit")
    public ResultData addPageInit(@RequestHeader("key") String key){

        String customerJson = stringRedisTemplate.boundValueOps(key).get();
        Customer customer = null;
        try {
        if (customerJson!=null&&!customerJson.isEmpty()){

            customer = mapper.readValue(customerJson, Customer.class);
            Map<String, Object> map = goodsService.addPageInit(customer);

            return ResultData.createSuccessResult(map);
        }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResultData.createFailResult(1111, "错误");
    }

    @RequestMapping(value = "/uploadImg",method = RequestMethod.POST)
    public String uploadImg(MultipartFile imgFiles){
        return goodsService.upload(imgFiles);
    }

    @RequestMapping("/updateGoods")
    public ResultData updateGoods(@RequestBody GoodsAdd goodsAdd){
        System.out.println(goodsAdd);

        try {
            //处理GoodsSku集合，生成Id并将GoodId绑定到集合元素的对象中
            List<GoodsSku> goodsSkus = goodsSkuService.initSkuList(goodsAdd.getGoodsSku(), goodsAdd.getGoods().getGoodId());;

            if(goodsService.updateGoods(goodsAdd.getGoods())>0&& goodsSkuService.insertSkus(goodsSkus)!=0){
                return ResultData.createSuccessResult(null);
            }else {
                return ResultData.createFailResult(ResultCode.NO_EFFECT, "受影响行数为0");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultData.createFailResult(ResultCode.EXPECTION,"异常");
        }
    }

    @RequestMapping(value = "/deleteGoods",method = RequestMethod.DELETE)
    public ResultData deleteGoods(@RequestParam String goodsId){
        ResultData resultData=null;
        int count = goodsService.deleteGoods(goodsId);
        if (count>0){
            resultData = ResultData.createSuccessResult(null);
        }else if (count==0){
            resultData = ResultData.createFailResult(ResultCode.NO_EFFECT,"受影响0行");
        }else {
            resultData = ResultData.createFailResult(ResultCode.NONE_ARGS,"id为null");
        }
        return resultData;
    }
}