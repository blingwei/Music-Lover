package com.blingwei.musicService.pojo;

import com.blingwei.musicService.enums.Type;

import java.util.Date;

public class Collect {
    private Integer id;
    private Integer userId;
    private Integer matterId;//操作的对象的id
    private Type type;//操作的类型
    private Date createDate;

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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
