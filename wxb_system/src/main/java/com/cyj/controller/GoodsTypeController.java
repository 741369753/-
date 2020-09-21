package com.cyj.controller;

import com.cyj.entity.GoodsType;
import com.cyj.entity.ResultCode;
import com.cyj.entity.ResultData;
import com.cyj.service.GoodsTypeService;
import com.github.pagehelper.PageInfo;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * author:aizhishang
 * time:2020/9/18
 */
@Controller
@RequestMapping("/goodsType")
public class GoodsTypeController {
    @Resource
    private GoodsTypeService goodsTypeService;

    @RequestMapping("/getgoodsType")
    @ResponseBody
    public Map<String, Object> getGoodsType(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        Map<String, Object> map = null;
        try {
            PageInfo pageInfo = goodsTypeService.queryType(page, limit);

            map = new HashMap<>();
            map.put("code", 0);
            map.put("data", pageInfo.getList());
            map.put("count", pageInfo.getTotal());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    @RequestMapping("/updateType")
    @ResponseBody
    public ResultData updateType(@RequestBody GoodsType goodsType) {
        if (goodsTypeService.updateType(goodsType) > 0) {
            return ResultData.createSuccessResult(null);
        } else {
            return ResultData.createFailResult(ResultCode.NO_EFFECT, "受影响行数为0");
        }
    }

    @RequestMapping("/insertType")
    @ResponseBody
    public ResultData insertType(@RequestBody GoodsType goodsType) {
        try {
            if (goodsTypeService.insertType(goodsType) > 0) {
                return ResultData.createSuccessResult(null);
            } else {
                return ResultData.createFailResult(ResultCode.NO_EFFECT, "受影响行数为0");
            }
        } catch (DuplicateKeyException e) {
            e.printStackTrace();
            return ResultData.createFailResult(ResultCode.DUPLICATEKEY, "分类ID已存在");
        }
    }

    @RequestMapping("/deleteType")
    @ResponseBody
    public ResultData deleteType(@RequestParam("typeId") String typeId) {
        ResultData resultData = null;
        if (typeId == null || typeId.isEmpty()) {
            return ResultData.createFailResult(ResultCode.NONE_ARGS, "id为空");
        }
        try {
            if (goodsTypeService.deleteType(typeId) > 0) {

                resultData = ResultData.createSuccessResult(null);
            } else {
                resultData = ResultData.createFailResult(ResultCode.NO_EFFECT, "受影响行数为0");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultData = ResultData.createFailResult(ResultCode.EXPECTION, "异常");
        }
        return resultData;
    }

}
