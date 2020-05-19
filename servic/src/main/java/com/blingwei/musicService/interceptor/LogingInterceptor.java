package com.blingwei.musicService.interceptor;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//public class LogingInterceptor implements HandlerInterceptor {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        if(HttpMethod.OPTIONS.toString().equals(request.getMethod())){
//            response.setStatus(HttpStatus.NO_CONTENT.value());
//            return true;
//        }
//        Subject subject = SecurityUtils.getSubject();
//        if(!subject.isAuthenticated()){
//            return false;
//        }
//        return true;
//}
//}
