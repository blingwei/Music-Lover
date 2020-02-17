package com.blingwei.musicService.bean.requestBaen;

import java.util.List;

public class ContributeForTopicRequest {
    private Integer topicId;
    private List<Integer> publishIdList;

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public List<Integer> getPublishIdList() {
        return publishIdList;
    }

    public void setPublishIdList(List<Integer> publishIdList) {
        this.publishIdList = publishIdList;
    }
}
