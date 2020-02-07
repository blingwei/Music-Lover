package com.blingwei.musicService.enums;

/**
 * @author liangwei
 * 表示用户的动作类型
 */

public enum OperateEnum implements BaseEnum<OperateEnum, Integer>{
    /**
     * 未知的类型
     */
    UN_KNOW(-1, "未知类型"),

    COLLECT(0,"收藏"),

    CANCEL_PICK(1, "关注"),


    PICK(2,"点赞"),

    PUBLISH(3, "发表"),

    VIEW(4, "浏览");



    private final Integer value;
    private final String message;


    private OperateEnum(Integer value, String message){
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

    public static OperateEnum valueOf(Integer value){
        for(OperateEnum typeEnum: OperateEnum.values()){

            if(typeEnum.getValue().equals(value)){
                return typeEnum;
            }
        }
        return UN_KNOW;
    }

    public static String getName(Integer value){
        for(OperateEnum typeEnum: OperateEnum.values()){
            if(typeEnum.getValue().equals(value)){
                return typeEnum.getMessage();
            }
        }
        return UN_KNOW.getMessage();
    }

}
