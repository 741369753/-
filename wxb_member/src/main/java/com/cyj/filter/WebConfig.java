package com.cyj.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * author:aizhishang
 * time:2020/9/20
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    //注入拦截器对象
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器并设置拦截路径
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**");
    }
}
