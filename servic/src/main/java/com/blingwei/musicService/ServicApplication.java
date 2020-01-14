package com.blingwei.musicService;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.blingwei.musicService.dao")
@EnableTransactionManagement //开启事务管理
@EnableCaching
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })//禁用spring security
public class ServicApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicApplication.class, args);
    }

}
