package com.blingwei.musicService.dao.redisService;

import java.util.List;

/**
 * @author liangwei
 */
public interface LikeRedisService {
    String ESSAY_WITH_SONG_LIKE_KEY = "essay_with_song_likes";
    String ESSAY_WITH_SONG_LIKE_NUM_KEY = "essay_with_song_num_likes";
    String COMMENT_LIKE_KEY = "comment_likes";
    String COMMENT_LIKE_NUM_KEY = "comment_num_likes";

    /**
     * 给音乐文章点赞
     * @param userId
     * @param matterId
     */
    void pickEssayWithSong(String userId, String matterId);

    /**
     * 给评论点赞
     * @param userId
     * @param matterId
     */
    void pickComment(String userId, String matterId);

    /**
     * 获取该音乐文章获取的点赞数
     * @param matterId
     * @return
     */
    Integer getPickEssayWithSongNum(String matterId);

    /**
     * 获取该评论获取的点赞数
     * @param matterId
     * @return
     */
    Integer getPickCommentNum(String matterId);

    /**
     * 获取该用户给该目标的点赞状态
     * @param userId
     * @param matterId
     * @return
     */
    Integer  getPickEssayWithSongStatus(String userId, String matterId);
    Integer  getPickCommentStatus(String userId, String matterId);
    void cancelPickEssayWithSong(String userId, String matterId);
    void cancelPickComment(String userId, String matterId);

}
