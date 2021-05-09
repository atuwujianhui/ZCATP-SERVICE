package com.fjzcit.zcatp.common.constant.http;

/**
 * CONTENT-TYPE
 */
public enum ContentTypeEnum {

    FORM(0, "application/x-www-form-urlencoded"),
    JSON(1, "application/json"),
    MULTIPART(2, "multipart/form-data"),
    TEXT(3, "text/xml");

    // 成员变量
    private int code;
    private String name;

    // 构造方法
    private ContentTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    // 普通方法
    public static String getName(int code) {
        for (ContentTypeEnum e : ContentTypeEnum.values()) {
            if (e.getCode() == code) {
                return e.name;
            }
        }
        return null;
    }

    public static ContentTypeEnum getEnum(int code) {
        for (ContentTypeEnum e : ContentTypeEnum.values()) {
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
