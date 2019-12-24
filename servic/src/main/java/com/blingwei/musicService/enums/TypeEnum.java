package com.blingwei.musicService.enums;

public enum TypeEnum implements BaseEnum<TypeEnum, Integer>{
    USER(0,"用户"),
    SONG(1, "歌曲"),
    ESSAYWITHSONG(2, "音乐文章");

    private final Integer value;
    private final String message;


    private TypeEnum(Integer value, String message){
        this.value = value;
        this.message = message;
    }

    @Override
    public Integer getValue() {
        return value;
    }


    @Override
    public String getMessage() {
        return message;
    }

}
