package com.blingwei.musicService.enums;

public enum TypeEnum implements BaseEnum<TypeEnum, Integer>{
    UN_KNOW(-1, "未知类型"),
    USER(0,"用户"),
    SONG(1, "歌曲"),
    ESSAY_WITH_SONG(2, "音乐文章"),
    COMMENT(3, "评论");

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


    public static TypeEnum valueOf(Integer value){
        for(TypeEnum typeEnum:TypeEnum.values()){

            if(typeEnum.getValue().equals(value)){
                return typeEnum;
            }
        }
        return UN_KNOW;
    }

    public static String getName(Integer value){
        for(TypeEnum typeEnum:TypeEnum.values()){
            if(typeEnum.getValue().equals(value)){
                return typeEnum.getMessage();
            }
        }
        return UN_KNOW.getMessage();
    }
}
