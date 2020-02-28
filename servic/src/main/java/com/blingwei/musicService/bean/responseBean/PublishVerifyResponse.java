package com.blingwei.musicService.bean.responseBean;

import com.blingwei.musicService.bean.commanBean.PublishVerifyBean;

import java.util.List;

public class PublishVerifyResponse {
    private List<PublishVerifyBean> publishVerifyBeans;
    private int nums;

    public List<PublishVerifyBean> getPublishVerifyBeans() {
        return publishVerifyBeans;
    }

    public void setPublishVerifyBeans(List<PublishVerifyBean> publishVerifyBeans) {
        this.publishVerifyBeans = publishVerifyBeans;
    }

    public int getNums() {
        return nums;
    }

    public void setNums(int nums) {
        this.nums = nums;
    }
}
