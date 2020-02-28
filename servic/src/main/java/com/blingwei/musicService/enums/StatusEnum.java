package com.blingwei.musicService.enums;

/**
 * @author liangwei
 * 表示用户发表的作品的状态
 */

public enum StatusEnum implements BaseEnum<StatusEnum, Integer>{
    /**
     * 未知的类型
     */
    UN_KNOW(-1, "未知类型"),

    VERIFY(0,"审核中"),

    PASS(1, "通过"),

    ERROR(2,"异常");

    private final Integer value;
    private final String message;


    private StatusEnum(Integer value, String message){
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

    public static StatusEnum valueOf(Integer value){
        for(StatusEnum typeEnum: StatusEnum.values()){

            if(typeEnum.getValue().equals(value)){
                return typeEnum;
            }
        }
        return UN_KNOW;
    }

    public static String getName(Integer value){
        for(StatusEnum typeEnum: StatusEnum.values()){
            if(typeEnum.getValue().equals(value)){
                return typeEnum.getMessage();
            }
        }
        return UN_KNOW.getMessage();
    }

}
