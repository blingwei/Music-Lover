package com.blingwei.musicService.bean.responseBean;

import com.blingwei.musicService.bean.commanBean.PublishManageBean;

import java.util.List;

public class PublishManageResponse {
    private List<PublishManageBean> publishManageBeans;
    private int nums;

    public List<PublishManageBean> getPublishManageBeans() {
        return publishManageBeans;
    }

    public void setPublishManageBeans(List<PublishManageBean> publishManageBeans) {
        this.publishManageBeans = publishManageBeans;
    }

    public int getNums() {
        return nums;
    }

    public void setNums(int nums) {
        this.nums = nums;
    }
}
