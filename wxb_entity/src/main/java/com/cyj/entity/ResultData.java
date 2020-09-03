package com.cyj.entity;

import lombok.Data;

/**
 * author:aizhishang
 * time:2020/8/26
 */
@Data
public class ResultData {
    private Integer code;
    private String msg;
    private Object data;

    public static ResultData createSuccessResult(Object data){
        ResultData resultData = new ResultData();
        resultData.code = 10000;
        resultData.data=data;

        return resultData;
    }

    public static ResultData createFailResult(Integer code,String msg){
        ResultData resultData = new ResultData();
        resultData.code = code;
        resultData.msg = msg;

        return resultData;
    }
}
