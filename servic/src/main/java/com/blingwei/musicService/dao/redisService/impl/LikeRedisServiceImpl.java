package com.blingwei.musicService.dao.redisService.impl;

import com.blingwei.musicService.dao.redisService.LikeRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


@Service
public class LikeRedisServiceImpl implements LikeRedisService {

    @Autowired
    private RedisTemplate redisTemplate;


    public void test(){
        Object s = redisTemplate.opsForValue().get("test");
        System.out.println(s);

    }

}
