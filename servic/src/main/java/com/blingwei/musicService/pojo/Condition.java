package com.blingwei.musicService.pojo;

//动态
public class Condition {
    private Integer id;
    private Integer userId;
    private String message;//描述
    private Integer operandId;//操作的id

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getOperandId() {
        return operandId;
    }

    public void setOperandId(Integer operandId) {
        this.operandId = operandId;
    }
}
