package com.blingwei.musicService.dao.redisService;

import java.util.List;

public interface LikeRedisService {
    String ESSAY_WITH_SONG_LIKE_KEY = "essay_with_song_likes";
    String ESSAY_WITH_SONG_LIKE_NUM_KEY = "essay_with_song_num_likes";
    String COMMENT_LIKE_KEY = "comment_likes";
    String COMMENT_LIKE_NUM_KEY = "comment_num_likes";
    void pickEssayWithSong(String userId, String matterId);
    void pickComment(String userId, String matterId);
    Integer getPickEssayWithSongNum(String matterId);
    Integer getPickCommentNum(String matterId);
    Integer  getPickEssayWithSongStatus(String userId, String matterId);
    Integer  getPickCommentStatus(String userId, String matterId);
    void cancelPickEssayWithSong(String userId, String matterId);
    void cancelPickComment(String userId, String matterId);

}
