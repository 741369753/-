package com.cyj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cyj.dao")//配置mybatis扫描dao层
public class WxbCustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxbCustomerApplication.class, args);
    }

}
