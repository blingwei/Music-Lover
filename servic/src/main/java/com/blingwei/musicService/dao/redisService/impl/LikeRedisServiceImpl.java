package com.blingwei.musicService.dao.redisService.impl;

import com.blingwei.musicService.dao.redisService.LikeRedisService;
import com.blingwei.musicService.enums.TypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LikeRedisServiceImpl implements LikeRedisService {

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public void pickEssayWithSong(String userId, String matterId) {
        String key = createKey(userId,matterId,TypeEnum.ESSAY_WITH_SONG);
        redisTemplate.opsForHash().put(ESSAY_WITH_SONG_LIKE_KEY, key, 1);
        String num_key = createNumKey(matterId,TypeEnum.ESSAY_WITH_SONG);
        redisTemplate.opsForHash().increment(ESSAY_WITH_SONG_LIKE_NUM_KEY, num_key, 1);
    }

    @Override
    public void pickComment(String userId, String matterId) {
        String key = createKey(userId,matterId,TypeEnum.COMMENT);
        redisTemplate.opsForHash().put(COMMENT_LIKE_KEY, key, 1);
        String num_key = createNumKey(matterId,TypeEnum.COMMENT);
        redisTemplate.opsForHash().increment(COMMENT_LIKE_NUM_KEY, num_key, 1);
    }

    @Override
    public Integer getPickEssayWithSongNum(String matterId) {
        return (Integer) redisTemplate.opsForHash().get(ESSAY_WITH_SONG_LIKE_NUM_KEY, createNumKey(matterId, TypeEnum.ESSAY_WITH_SONG));
    }

    @Override
    public Integer getPickCommentNum(String matterId) {
        return (Integer) redisTemplate.opsForHash().get(COMMENT_LIKE_NUM_KEY, createNumKey(matterId, TypeEnum.COMMENT));
    }

    @Override
    public Integer getPickEssayWithSongStatus(String userId, String matterId) {
        String key  = createKey(userId,matterId,TypeEnum.ESSAY_WITH_SONG);
        return (Integer) redisTemplate.opsForHash().get(ESSAY_WITH_SONG_LIKE_KEY, key);
    }

    @Override
    public Integer getPickCommentStatus(String userId, String matterId) {
        String key = createKey(userId, matterId, TypeEnum.COMMENT);
        return (Integer) redisTemplate.opsForHash().get(COMMENT_LIKE_NUM_KEY,key);
    }


    @Override
    public void cancelPickEssayWithSong(String userId, String matterId) {
        String key = createKey(userId,matterId,TypeEnum.ESSAY_WITH_SONG);
        redisTemplate.opsForHash().put(ESSAY_WITH_SONG_LIKE_KEY, key, 0);
        String num_key = createNumKey(matterId,TypeEnum.ESSAY_WITH_SONG);
        redisTemplate.opsForHash().increment(ESSAY_WITH_SONG_LIKE_NUM_KEY, num_key, -1);
    }

    @Override
    public void cancelPickComment(String userId, String matterId) {
        String key = createKey(userId,matterId,TypeEnum.COMMENT);
        redisTemplate.opsForHash().put(COMMENT_LIKE_KEY, key, 1);
        String num_key = createNumKey(matterId,TypeEnum.COMMENT);
        redisTemplate.opsForHash().increment(COMMENT_LIKE_NUM_KEY, num_key, -1);
    }

    private String createKey(String userId, String matterId, TypeEnum type){
        return userId+":"+matterId+":"+type.getValue();
    }

    private String createNumKey(String matterId, TypeEnum type){
        return matterId+":"+type.getValue();
    }

}
