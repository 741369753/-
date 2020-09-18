package com.cyj.controller;

import com.cyj.entity.Member;
import com.cyj.entity.ResultCode;
import com.cyj.entity.ResultData;
import com.cyj.service.MemberService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    private ObjectMapper mapper = new ObjectMapper();

    @RequestMapping("/login")
    public ResultData login(@RequestBody Member member) {

        try {
            member = memberService.login(member.getAccount(), member.getPassword());
            if (member==null){
                return ResultData.createFailResult(ResultCode.NONE, "用户名、密码错误");
            }

            String key = UUID.randomUUID().toString().replace("-", "");
            stringRedisTemplate.boundValueOps(key).set(mapper.writeValueAsString(member),30, TimeUnit.MINUTES);
            return ResultData.createSuccessResult(member);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResultData.createFailResult(ResultCode.EXPECTION, "异常");
        }
    }
}
