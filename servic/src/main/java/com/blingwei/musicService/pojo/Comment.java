package com.blingwei.musicService.pojo;

import com.blingwei.musicService.enums.TypeEnum;

import java.util.Date;

public class Comment {
    private Integer id;
    private Integer userId;
    private Integer matterId;//操作的对象的id
    private Integer pid; //pi
    private Integer replyId; //回复的人的id
    private TypeEnum type;//操作的类型
    private Date createDate;
    private String content;
    private Integer pickNum;//点赞数

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMatterId() {
        return matterId;
    }

    public void setMatterId(Integer matterId) {
        this.matterId = matterId;
    }

    public TypeEnum getType() {
        return type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public Integer getPickNum() {
        return pickNum;
    }

    public void setPickNum(Integer pickNum) {
        this.pickNum = pickNum;
    }
}
