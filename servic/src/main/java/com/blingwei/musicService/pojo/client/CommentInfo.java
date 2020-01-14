package com.blingwei.musicService.pojo.client;

public class CommentInfo {
    private Integer id;
    private Integer userId;
    private String username;
    private Integer matterId;
    private Integer pid;
    private Integer replyId;
    private String replayName;
    private String type;
    private String content;
    private String createDate;
    private Integer pickNum;
    private boolean pickStatus;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getMatterId() {
        return matterId;
    }

    public void setMatterId(Integer matterId) {
        this.matterId = matterId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReplayName() {
        return replayName;
    }

    public void setReplayName(String replayName) {
        this.replayName = replayName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Integer getPickNum() {
        return pickNum;
    }

    public void setPickNum(Integer pickNum) {
        this.pickNum = pickNum;
    }

    public boolean isPickStatus() {
        return pickStatus;
    }

    public void setPickStatus(boolean pickStatus) {
        this.pickStatus = pickStatus;
    }
}
