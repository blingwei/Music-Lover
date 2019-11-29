package com.blingwei.musicService;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@MapperScan("com.blingwei.musicService.dao")
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })//禁用spring security
public class ServicApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicApplication.class, args);
    }

}
