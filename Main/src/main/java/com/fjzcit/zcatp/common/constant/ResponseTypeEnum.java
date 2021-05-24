package com.fjzcit.zcatp.common.constant;

public enum ResponseTypeEnum {
    STRING(0, "字符串"),
    JSON(1, "JSON");

    // 成员变量
    private int code;
    private String name;

    // 构造方法
    private ResponseTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    // 普通方法
    public static String getName(int code) {
        for (ResponseTypeEnum e : ResponseTypeEnum.values()) {
            if (e.getCode() == code) {
                return e.name;
            }
        }
        return null;
    }

    public static ResponseTypeEnum getEnum(int code) {
        for (ResponseTypeEnum e : ResponseTypeEnum.values()) {
            if (e.getCode() == code) {
                return e;
            }
        }
        return null;
    }

    // get set 方法
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
}
