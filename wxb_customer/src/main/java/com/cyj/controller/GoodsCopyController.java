package com.cyj.controller;

import com.cyj.entity.Customer;
import com.cyj.entity.GoodsCopy;
import com.cyj.entity.ResultCode;
import com.cyj.entity.ResultData;
import com.cyj.service.GoodsCopyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Random;

/**
 * author:aizhishang
 * time:2020/9/17
 */
@RestController
@CrossOrigin
@RequestMapping("/copy")
public class GoodsCopyController {
    @Resource
    private GoodsCopyService goodsCopyService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private ObjectMapper mapper = new ObjectMapper();

    @RequestMapping("/getGoodsCopy")
    public ResultData getGoodsCopy(@RequestHeader("key") String key, @RequestParam("search") String search, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit) throws JsonProcessingException {
        Customer customer = mapper.readValue(stringRedisTemplate.boundValueOps(key).get(), Customer.class);
        PageInfo pageInfo = goodsCopyService.queryGoodsCopyPageLike(search, customer.getCustomerId(), page, limit);
        return ResultData.createSuccessResult(pageInfo);
    }

    @RequestMapping(value = "/updateGoodsCopy")
    public ResultData updateGoodsCopy(@RequestBody GoodsCopy goodsCopy){
        if (goodsCopyService.updateGoodsCopy(goodsCopy)>0) {
            return ResultData.createSuccessResult(null);
        }

        return ResultData.createFailResult(ResultCode.NO_EFFECT, "受影响行数0");
    }

    @RequestMapping(value = "/deleteGoodsCopy",method = RequestMethod.DELETE)
    public ResultData deleteGoodsCopy(@RequestParam Integer copyId){
        if (goodsCopyService.deleteGoodsCopy(copyId)>0) {
            return ResultData.createSuccessResult(null);
        }else {
            return ResultData.createFailResult(ResultCode.NO_EFFECT, "受影响行数0");
        }
    }

    @RequestMapping("/addGoodsCopy")
    public ResultData addGoodsCopy(@RequestHeader("key") String key,@RequestBody GoodsCopy goodsCopy) throws JsonProcessingException {
        Customer customer = mapper.readValue(stringRedisTemplate.boundValueOps(key).get(), Customer.class);

        int id = new Random().nextInt(1000000000);
        goodsCopy.setCopyId(id);
        goodsCopy.setCustomerId(customer.getCustomerId());
        if (goodsCopyService.addGoodsCopy(goodsCopy)>0) {
            return ResultData.createSuccessResult(null);
        }else {
            return ResultData.createFailResult(ResultCode.NO_EFFECT, "受影响行数0");
        }
    }
}
