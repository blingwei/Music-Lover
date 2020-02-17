package com.blingwei.musicService.pojo;

import com.blingwei.musicService.enums.OperateEnum;
import com.blingwei.musicService.enums.TypeEnum;

import java.util.Date;

public class Condition {
    private Integer id;

    private Integer userId;

    private OperateEnum operate;

    private TypeEnum type;

    private Integer matterId;

    private Date createTime;

    private Integer isDelete;

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

    public OperateEnum getOperate() {
        return operate;
    }

    public void setOperate(OperateEnum operate) {
        this.operate = operate;
    }

    public TypeEnum getType() {
        return type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    public Integer getMatterId() {
        return matterId;
    }

    public void setMatterId(Integer matterId) {
        this.matterId = matterId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}