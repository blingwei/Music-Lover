package com.blingwei.musicService.pojo;

import java.util.Date;
import java.util.List;

public class Topic {
    private Integer id;
    private String title;//标题
    private String intor;//简介
    private Date createDate;
    private List<Category> categoryList;
    private List<EssayWithSong> essayWithSongList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntor() {
        return intor;
    }

    public void setIntor(String intor) {
        this.intor = intor;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<EssayWithSong> getEssayWithSongList() {
        return essayWithSongList;
    }

    public void setEssayWithSongList(List<EssayWithSong> essayWithSongList) {
        this.essayWithSongList = essayWithSongList;
    }
}
