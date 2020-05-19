package com.blingwei.musicService.enums;

/**
 * @author liangwei
 */

public enum SexEnum implements BaseEnum<SexEnum, Integer> {
    /**
     * 未知的类型
     */
    UN_KNOW(-1, "未知类型"),
    /**
     * 男性
     */
    MALE(0, "男性"),
    /**
     * 女性
     */
    FEMALE(1, "女性");


    private final Integer value;
    private final String message;


    private SexEnum(Integer value, String message) {
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

    public static SexEnum valueOf(Integer value) {
        for (SexEnum typeEnum : SexEnum.values()) {

            if (typeEnum.getValue().equals(value)) {
                return typeEnum;
            }
        }
        return UN_KNOW;
    }

    public static String getName(Integer value) {
        for (SexEnum typeEnum : SexEnum.values()) {
            if (typeEnum.getValue().equals(value)) {
                return typeEnum.getMessage();
            }
        }
        return UN_KNOW.getMessage();
    }

}
