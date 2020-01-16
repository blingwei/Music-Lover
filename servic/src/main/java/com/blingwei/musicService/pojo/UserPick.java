package com.blingwei.musicService.pojo;

import com.blingwei.musicService.enums.PickStatusEnum;
import com.blingwei.musicService.enums.TypeEnum;

import java.util.Date;

/**
 * @author liangwei
 */
public class UserPick {
    private Integer id;
    private Integer userId;
    private Integer matterId;
    private TypeEnum type;
    private Date createTime;
    private PickStatusEnum pickStatus;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public PickStatusEnum getPickStatus() {
        return pickStatus;
    }

    public void setPickStatus(PickStatusEnum pickStatus) {
        this.pickStatus = pickStatus;
    }
}
