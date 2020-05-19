package com.blingwei.musicService.annotation;


import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserIdDeCode {
    String value();//加密的类型
}
