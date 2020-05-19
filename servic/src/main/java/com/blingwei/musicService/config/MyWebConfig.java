package com.blingwei.musicService.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootConfiguration
public class MyWebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowCredentials(true)
            .allowedOrigins("http://localhost:8080")
            .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
            .allowedHeaders("*")
            .maxAge(3600);
    }
}
