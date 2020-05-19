package com.blingwei.musicService.dao.redisService.impl;

import com.blingwei.musicService.dao.ConditionMapper;
import com.blingwei.musicService.dao.redisService.LikeRedisService;
import com.blingwei.musicService.enums.OperateEnum;
import com.blingwei.musicService.enums.TypeEnum;
import com.blingwei.musicService.pojo.Condition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author liangwei
 */
@Service
public class LikeRedisServiceImpl implements LikeRedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ConditionMapper conditionMapper;


    @Override
    public void pickEssayWithSong(String userId, String matterId) {
        String key = createKey(userId, matterId, TypeEnum.ESSAY_WITH_SONG);
        redisTemplate.opsForHash().put(ESSAY_WITH_SONG_LIKE_KEY, key, 1);
        String num_key = createNumKey(matterId, TypeEnum.ESSAY_WITH_SONG);
        redisTemplate.opsForHash().increment(ESSAY_WITH_SONG_LIKE_NUM_KEY, num_key, 1);
        Condition condition = new Condition();
        condition.setUserId(Integer.parseInt(userId));
        condition.setMatterId(Integer.parseInt(matterId));
        condition.setType(TypeEnum.ESSAY_WITH_SONG);
        condition.setOperate(OperateEnum.PICK);
        conditionMapper.insert(condition);
    }

    @Override
    public void pickComment(String userId, String matterId) {
        String key = createKey(userId, matterId, TypeEnum.COMMENT);
        redisTemplate.opsForHash().put(COMMENT_LIKE_KEY, key, 1);
        String num_key = createNumKey(matterId, TypeEnum.COMMENT);
        redisTemplate.opsForHash().increment(COMMENT_LIKE_NUM_KEY, num_key, 1);
        Condition condition = new Condition();
        condition.setUserId(Integer.parseInt(userId));
        condition.setMatterId(Integer.parseInt(matterId));
        condition.setType(TypeEnum.COMMENT);
        condition.setOperate(OperateEnum.PICK);
        conditionMapper.insert(condition);
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
        String key = createKey(userId, matterId, TypeEnum.ESSAY_WITH_SONG);
        return (Integer) redisTemplate.opsForHash().get(ESSAY_WITH_SONG_LIKE_KEY, key);
    }

    @Override
    public Integer getPickCommentStatus(String userId, String matterId) {
        String key = createKey(userId, matterId, TypeEnum.COMMENT);
        return (Integer) redisTemplate.opsForHash().get(COMMENT_LIKE_KEY, key);
    }


    @Override
    public void cancelPickEssayWithSong(String userId, String matterId) {
        String key = createKey(userId, matterId, TypeEnum.ESSAY_WITH_SONG);
        redisTemplate.opsForHash().put(ESSAY_WITH_SONG_LIKE_KEY, key, 0);
        String num_key = createNumKey(matterId, TypeEnum.ESSAY_WITH_SONG);
        redisTemplate.opsForHash().increment(ESSAY_WITH_SONG_LIKE_NUM_KEY, num_key, -1);
    }

    @Override
    public void cancelPickComment(String userId, String matterId) {
        String key = createKey(userId, matterId, TypeEnum.COMMENT);
        redisTemplate.opsForHash().put(COMMENT_LIKE_KEY, key, 0);
        String num_key = createNumKey(matterId, TypeEnum.COMMENT);
        redisTemplate.opsForHash().increment(COMMENT_LIKE_NUM_KEY, num_key, -1);
    }

    @Override
    public Map getAllEssayWithSongPick() {
        return redisTemplate.opsForHash().entries(ESSAY_WITH_SONG_LIKE_KEY);
    }

    @Override
    public Map getAllCommentPick() {
        return redisTemplate.opsForHash().entries(COMMENT_LIKE_KEY);
    }

    @Override
    public void setEssayWithSongLikeNum(String matterId, int num) {
        String num_key = createNumKey(matterId, TypeEnum.ESSAY_WITH_SONG);
        redisTemplate.opsForHash().put(ESSAY_WITH_SONG_LIKE_NUM_KEY, num_key, num);
    }

    @Override
    public void setCommentLikeNum(String matterId, int num) {
        String num_key = createNumKey(matterId, TypeEnum.COMMENT);
        redisTemplate.opsForHash().put(COMMENT_LIKE_NUM_KEY, num_key, num);
    }

    @Override
    public void clean() {
        redisTemplate.delete(ESSAY_WITH_SONG_LIKE_KEY);
        redisTemplate.delete(ESSAY_WITH_SONG_LIKE_NUM_KEY);
        redisTemplate.delete(COMMENT_LIKE_KEY);
        redisTemplate.delete(COMMENT_LIKE_NUM_KEY);
    }


    private String createKey(String userId, String matterId, TypeEnum type) {
        return userId + ":" + matterId + ":" + type.getValue();
    }

    private String createNumKey(String matterId, TypeEnum type) {
        return matterId + ":" + type.getValue();
    }

}
