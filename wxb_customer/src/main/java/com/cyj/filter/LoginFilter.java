package com.cyj.filter;

import com.cyj.entity.ResultCode;
import com.cyj.entity.ResultData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * author:aizhishang
 * time:2020/9/15
 */

@Component
@WebFilter("/")
public class LoginFilter implements Filter {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (request.getServletPath().endsWith("/login")) {
            filterChain.doFilter(request, response);
            return;
        }else {
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

                //设置response后再获取PrintWriter
                PrintWriter out = response.getWriter();

                out.print(new ObjectMapper().writeValueAsString(new ResultData(ResultCode.NO_LOGIN, "未登录", null)));
                out.close();
                out.flush();

                return;
            }
            String userJson = stringRedisTemplate.boundValueOps(key).get();
            if (userJson==null||userJson.isEmpty()){
                response.setHeader("Access-Control-Allow-Origin","*");      //设置允许跨域
                response.setHeader("Access-Control-Allow-Methods","*");     //设置允许多种请求方式
                response.setHeader("Access-Control-Max-Age","3600");        //设置跨域缓存的最大时间
                response.setHeader("Access-Control-Allow-Headers","*");     //设置允许携带header
                response.setHeader("Access-Control-Allow-Credentials","*"); //设置允许携带cookie
                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");

                PrintWriter out = response.getWriter();

                out.print(new ObjectMapper().writeValueAsString(new ResultData(ResultCode.NO_LOGIN, "未登录", null)));
                out.close();
                out.flush();
            }else {
                //超时时间内做出操作重新计算超时时间(更新redis的有效时间)
                stringRedisTemplate.boundValueOps(key).expire(30, TimeUnit.MINUTES);
                filterChain.doFilter(request, response);
            }
        }
    }
}
