package com.blingwei.musicService.pojo;

public class Recommend {
    private Integer id;

    private Integer essaySongId;

    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEssaySongId() {
        return essaySongId;
    }

    public void setEssaySongId(Integer essaySongId) {
        this.essaySongId = essaySongId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
}