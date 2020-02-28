package com.blingwei.musicService.bean.responseBean;

import com.blingwei.musicService.pojo.Song;

public class AdminViewPublishResponse {
    private String content;
    private Integer songId;
    private Song song;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getSongId() {
        return songId;
    }

    public void setSongId(Integer songId) {
        this.songId = songId;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }
}

