package com.fjzcit.zcatp.common.constant.http;

import com.fjzcit.zcatp.common.constant.InterfaceTypeEnum;

/**
 * REQUEST-METHOD（请求方法）
 */
public enum RequestMethodEnum{
    GET(0, "GET"),
    POST(1, "POST"),
    PUT(2, "PUT"),
    DELETE(3, "DELE");

    // 成员变量
    private int code;
    private String name;

    // 构造方法
    private RequestMethodEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    // 普通方法
    public static String getName(int code) {
        for (RequestMethodEnum c : RequestMethodEnum.values()) {
            if (c.getCode() == code) {
                return c.name;
            }
        }
        return null;
    }

    public static RequestMethodEnum getEnum(int code) {
        for (RequestMethodEnum c : RequestMethodEnum.values()) {
            if (c.getCode() == code) {
                return c;
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
