package com.cyj.filter;

import com.cyj.entity.ResultCode;
import com.cyj.entity.ResultData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * author:aizhishang
 * time:2020/9/20
 */
@Component//交由spring管理，并需要一个配置类（这里是WebConfig）
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
        拦截器执行流程:
            1.preHandle:拦截请求，在执行controller之前，返回true表示继续执行后续代友，false表示拦截，后续代码不会执行
            2.postHandle：执行controller之后，渲染视图前
            3.afterCompletion：渲染视图后
            4. afterConcurrentHandlingStarted
            5.

     */


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getServletPath().endsWith("login")||request.getServletPath().endsWith("register")) {
            return true;
        }
        String key = request.getHeader("key");
        if (key==null){
            //getWriter不能写在if外，filterChain.dofilter方法前不能使用getWriter()方法

            response.setHeader("Access-Control-Allow-Origin","*");      //设置允许跨域
            response.setHeader("Access-Control-Allow-Methods","*");     //设置允许多种请求方式
            response.setHeader("Access-Control-Max-Age","3600");        //设置跨域缓存的最大时间
            response.setHeader("Access-Control-Allow-Headers","*");     //设置允许携带header
            response.setHeader("Access-Control-Allow-Credentials","*"); //设置允许携带cookie
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");

            PrintWriter out = response.getWriter();
            out.println(new ObjectMapper().writeValueAsString(ResultData.createFailResult(ResultCode.NO_LOGIN, "未登录")));
            out.flush();
            out.close();
            return false;
        }

        String userJson = stringRedisTemplate.boundValueOps(key).get();
        if (userJson==null||userJson==""){
            response.setHeader("Access-Control-Allow-Origin","*");      //设置允许跨域
            response.setHeader("Access-Control-Allow-Methods","*");     //设置允许多种请求方式
            response.setHeader("Access-Control-Max-Age","3600");        //设置跨域缓存的最大时间
            response.setHeader("Access-Control-Allow-Headers","*");     //设置允许携带header
            response.setHeader("Access-Control-Allow-Credentials","*"); //设置允许携带cookie
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");

            PrintWriter out = response.getWriter();
            out.println(new ObjectMapper().writeValueAsString(ResultData.createFailResult(ResultCode.NO_LOGIN, "未登录")));
            out.flush();
            out.close();
            return false;
        }

        stringRedisTemplate.boundValueOps(key).expire(30, TimeUnit.MINUTES);
        return true;
    }
}
