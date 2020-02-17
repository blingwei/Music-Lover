package com.blingwei.musicService.dao;

import com.blingwei.musicService.bean.commanBean.AttentionResponse;
import com.blingwei.musicService.bean.responseBean.EssayWithSongCardResponse;
import com.blingwei.musicService.pojo.Condition;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConditionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Condition record);

    Condition selectByPrimaryKey(Integer id);


    List<Condition> selectAll();

    List<Condition> selectByUserId(Integer userId);

    int updateByPrimaryKey(Condition record);

    EssayWithSongCardResponse findConditionMessageByEssayWithSongId(Integer essayWithSongId);

    EssayWithSongCardResponse findConditionMessageByCommentId(Integer commentId);

    AttentionResponse findConditionMessageByUserId(Integer userId);

    AttentionResponse findConditionMessageByTopicId(Integer topicId);

}