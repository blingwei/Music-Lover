package com.blingwei.musicService.bean.responseBean;

import com.blingwei.musicService.bean.commanBean.ConditionMessageBean;

public class ConditionResponse {
    private String operate;
    private String type;
    private Integer matterId;
    private String matterName;
    private ConditionMessageBean conditionMessage;

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getMatterId() {
        return matterId;
    }

    public void setMatterId(Integer matterId) {
        this.matterId = matterId;
    }

    public String getMatterName() {
        return matterName;
    }

    public void setMatterName(String matterName) {
        this.matterName = matterName;
    }

    public ConditionMessageBean getConditionMessage() {
        return conditionMessage;
    }

    public void setConditionMessage(ConditionMessageBean conditionMessage) {
        this.conditionMessage = conditionMessage;
    }
}
