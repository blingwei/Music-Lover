package com.blingwei.musicService.bean.commanBean;

public class TopicInfoBean {
    private String name;
    private String introduce;
    private String url; //封面图片的url
    private int productionNum;
    private boolean status; //是否被自己关注
    private boolean identity; //是否是自己的专栏
    private Integer userId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public int getProductionNum() {
        return productionNum;
    }

    public void setProductionNum(int productionNum) {
        this.productionNum = productionNum;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isIdentity() {
        return identity;
    }

    public void setIdentity(boolean identity) {
        this.identity = identity;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
