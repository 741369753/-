package com.cyj.controller;

import com.cyj.entity.Customer;
import com.cyj.entity.ResultCode;
import com.cyj.entity.ResultData;
import com.cyj.service.CustomerService;
import com.cyj.utils.VerifyCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.UUID;

/**
 * author:aizhishang
 * time:2020/9/14
 */
@RestController
//设置跨域
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private CustomerService customerService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResultData login(@RequestBody Customer customer) {
        ResultData resultData = null;
        try {
            Customer queryCustomer = customerService.queryByLoginName(customer);
            if (queryCustomer != null) {
                //将用户信息存入redis
                String key = UUID.randomUUID().toString().replace("-", "");
                stringRedisTemplate.boundValueOps(key).set(new ObjectMapper().writeValueAsString(queryCustomer));
                resultData = ResultData.createSuccessResult(key);
            } else {
                resultData = ResultData.createFailResult(ResultCode.
                        CUSTOMER_LOINFAIL, "用户名密码错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultData = ResultData.createFailResult(ResultCode.EXPECTION, "出现异常");
        }

        return resultData;
    }

    @RequestMapping("/code")
    public void getCode(HttpServletResponse response) throws IOException {
        //创建验证码VerifyCode对象
        VerifyCode vc = new VerifyCode();
        //获取图片对象
        BufferedImage bi = vc.getImage();
        //获得图片的文本内容
        String text = vc.getText();

        //向浏览器输出图片
        ImageIO.write(bi,"JPEG", response.getOutputStream());

    }

    //@GetMapping("/lgin2/{username}/{pwd}")
    //public String login2(@PathVariable("username") String usernmae,@PathVariable("pwd") String pwd){
    //    return usernmae+"  "+pwd;
    //}
}