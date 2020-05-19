package com.blingwei.musicService.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(public *  com.blingwei.musicService.controller.*.*(*)) ")
    public void log() {

    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        logger.info("start--------------------------------");

    }

}
