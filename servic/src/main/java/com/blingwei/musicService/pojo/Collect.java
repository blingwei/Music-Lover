package com.blingwei.musicService.pojo;

import com.blingwei.musicService.enums.TypeEnum;

import java.util.Date;

public class Collect {
    private Integer userId;
    private Integer matterId;//操作的对象的id
    private TypeEnum type;//操作的类型
    private Date createTime;


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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
