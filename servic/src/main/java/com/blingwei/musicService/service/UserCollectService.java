package com.blingwei.musicService.service;

import com.blingwei.musicService.bean.commanBean.AttentionResponse;
import com.blingwei.musicService.bean.responseBean.EssayWithSongCardResponse;
import com.blingwei.musicService.pojo.Collect;

import java.util.List;

/**
 * @author 86187
 */
public interface UserCollectService {
    int addCollect(Collect collect);

    int deleteCollect(Collect collect);

    int getCollectNumByMatterIdAndType(Collect collect);

    Collect getCollect(Collect collect);

    Collect getCollectWithoutIsDelete(Collect collect);

    List<EssayWithSongCardResponse> findCollectInfos(Integer userId);

    List<AttentionResponse> findMyAttentions(Integer userId);

    List<AttentionResponse> findAttentionMes(Integer userId);

    List<AttentionResponse> findAttentionTopics(Integer userId);

    int getAttentionNumByUserId(Integer userId);

    int getAttentionNumByTopicId(Integer topicId);
}
