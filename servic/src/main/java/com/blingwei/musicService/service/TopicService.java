package com.blingwei.musicService.service;

import com.blingwei.musicService.bean.commanBean.TopicInfoBean;
import com.blingwei.musicService.bean.requestBaen.ContributeForTopicRequest;
import com.blingwei.musicService.bean.responseBean.EssayWithSongCardResponse;
import com.blingwei.musicService.pojo.Topic;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TopicService {
    int addTopic(Topic topic);

    List<Topic> findTopicByUserId(Integer userId);

    Topic findTopicById(Integer id);

    TopicInfoBean findTopicInfoById(Integer id);

    List<EssayWithSongCardResponse> findPublishByTopicId(Integer id);

    void contributeForTopic(ContributeForTopicRequest request);

    int findPublishNumById(Integer id);

}
