package com.blingwei.musicService.pojo;

public class EssayForElastic {
    private Integer id;//文章id
    private String title;
    private String intor;
    private String content;
    private String songName;
    private String userName;

    public EssayForElastic() {
    }

    public EssayForElastic(Integer id, String title, String intor, String content, String songName, String userName) {
        this.id = id;
        this.title = title;
        this.intor = intor;
        this.content = content;
        this.songName = songName;
        this.userName = userName;
    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
