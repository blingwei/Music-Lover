package com.blingwei.musicService.bean.responseBean;

import com.blingwei.musicService.bean.commanBean.TopicInfoBean;

import java.util.List;

public class TopicInfoResponse {
    private TopicInfoBean topicInfo;
    private List<EssayWithSongCardResponse> publishList;

    public TopicInfoBean getTopicInfo() {
        return topicInfo;
    }

    public void setTopicInfo(TopicInfoBean topicInfo) {
        this.topicInfo = topicInfo;
    }

    public List<EssayWithSongCardResponse> getPublishList() {
        return publishList;
    }

    public void setPublishList(List<EssayWithSongCardResponse> publishList) {
        this.publishList = publishList;
    }
}
