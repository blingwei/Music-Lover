package com.blingwei.musicService.bean.responseBean;


import com.blingwei.musicService.bean.commanBean.ConditionMessageBean;

/**
 * @author 86187
 */
public class EssayWithSongCardResponse extends ConditionMessageBean {
    private String title;
    private String userName;
    private String content;
    private Integer id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
