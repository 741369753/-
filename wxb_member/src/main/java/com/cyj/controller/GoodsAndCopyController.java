package com.cyj.controller;

import com.cyj.entity.ResultData;
import com.cyj.service.GoodsAndCopyService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * author:aizhishang
 * time:2020/9/20
 */
@RestController
@CrossOrigin
@RequestMapping("/goodsAndCopy")
public class GoodsAndCopyController {
    @Resource
    private GoodsAndCopyService goodsAndCopyService;

    @RequestMapping("/init")
    public ResultData init(){
        return ResultData.createSuccessResult(goodsAndCopyService.init());
    }
}
