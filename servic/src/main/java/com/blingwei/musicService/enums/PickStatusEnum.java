package com.blingwei.musicService.enums;

public enum PickStatusEnum implements BaseEnum<PickStatusEnum, Integer>{
    UN_KNOW(-1, "未知类型"),
    PICK(0,"点赞"),
    CANCEL_PICK(1, "取消点赞");



    private final Integer value;
    private final String message;


    private PickStatusEnum(Integer value, String message){
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

    public static PickStatusEnum valueOf(Integer value){
        for(PickStatusEnum typeEnum:PickStatusEnum.values()){

            if(typeEnum.getValue().equals(value)){
                return typeEnum;
            }
        }
        return UN_KNOW;
    }

    public static String getName(Integer value){
        for(PickStatusEnum typeEnum:PickStatusEnum.values()){
            if(typeEnum.getValue().equals(value)){
                return typeEnum.getMessage();
            }
        }
        return UN_KNOW.getMessage();
    }

}
