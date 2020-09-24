package com.cyj.controller;

import com.cyj.entity.Customer;
import com.cyj.entity.Member;
import com.cyj.entity.ResultCode;
import com.cyj.entity.ResultData;
import com.cyj.service.MemberService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * author:aizhishang
 * time:2020/9/18
 */
@RestController
@CrossOrigin
@RequestMapping("/member")
public class MemberController {
    @Resource
    private MemberService memberService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private ObjectMapper mapper;

    @RequestMapping("/login")
    public ResultData login(@RequestBody Customer customer) {

        try {
            Member member = memberService.login(customer.getLoginName(), customer.getLoginPwd());
            if (member==null){
                return ResultData.createFailResult(ResultCode.NONE_ARGS, "用户名、密码错误");
            }

            String key = UUID.randomUUID().toString().replace("-", "");
            stringRedisTemplate.boundValueOps(key).set(mapper.writeValueAsString(member),30, TimeUnit.MINUTES);
            return ResultData.createSuccessResult(key);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResultData.createFailResult(ResultCode.EXPECTION, "异常");
        }
    }

    @RequestMapping("/register")
    public ResultData register(@RequestBody Member member){
        try {
            if (memberService.register(member)>0) {
                return ResultData.createSuccessResult(null);
            }else {
                return ResultData.createFailResult(ResultCode.NO_EFFECT, "受影响行数0");
            }
        }catch (DuplicateKeyException e){
            e.printStackTrace();
            return ResultData.createFailResult(ResultCode.DUPLICATEKEY,"用户名已存在");
        }catch (Exception e) {
            e.printStackTrace();
            return ResultData.createFailResult(ResultCode.EXPECTION, "异常");
        }
    }
}
