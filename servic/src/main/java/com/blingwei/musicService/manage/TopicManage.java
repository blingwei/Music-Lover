package com.blingwei.musicService.manage;

import com.blingwei.musicService.bean.commanBean.TopicInfoBean;
import com.blingwei.musicService.bean.requestBaen.ContributeForTopicRequest;
import com.blingwei.musicService.bean.responseBean.EssayWithSongCardResponse;
import com.blingwei.musicService.bean.responseBean.TopicInfoResponse;
import com.blingwei.musicService.enums.TypeEnum;
import com.blingwei.musicService.service.TopicService;
import com.blingwei.musicService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TopicManage {
    @Autowired
    private TopicService topicService;

    @Autowired
    private UserCollectManage userCollectManage;

    @Autowired
    private UserService userService;

    public TopicInfoResponse getTopicInfo(Integer id){
        TopicInfoResponse topicInfoResponse = new TopicInfoResponse();
        TopicInfoBean topicInfoBean = topicService.findTopicInfoById(id);
        topicInfoBean.setStatus(userCollectManage.meIsAttentionUser(id, TypeEnum.TOPIC));
        if(topicInfoBean.getUserId().equals(userService.getCurrentUser().getId())){
            topicInfoBean.setIdentity(true);
        }else{
            topicInfoBean.setIdentity(false);
        }
        List<EssayWithSongCardResponse> publishList = topicService.findPublishByTopicId(id);
        topicInfoResponse.setTopicInfo(topicInfoBean);
        topicInfoResponse.setPublishList(publishList);
        return topicInfoResponse;
    }

    public void contributeForTopic(ContributeForTopicRequest contributeForTopicRequest){
        topicService.contributeForTopic(contributeForTopicRequest);
    }

}
