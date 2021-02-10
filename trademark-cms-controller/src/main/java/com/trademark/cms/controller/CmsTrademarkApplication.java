package com.trademark.cms.controller;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.trademark.cms.dao.mapper")
@ComponentScan(basePackages = {"com.trademark"})
public class CmsTrademarkApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsTrademarkApplication.class, args);
    }
}
