package com.blingwei.musicService.enums;

public enum Type {
    USER(0,"用户"),
    SONG(1, "歌曲"),
    ESSAYWITHSONG(2, "音乐文章");

    private Integer value;
    private String message;

    private Type(Integer value, String message){
        this.value = value;
        this.message = message;
    }

}
