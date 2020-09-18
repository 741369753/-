package com.cyj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cyj.dao")
public class WxbMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxbMemberApplication.class, args);
    }

}
