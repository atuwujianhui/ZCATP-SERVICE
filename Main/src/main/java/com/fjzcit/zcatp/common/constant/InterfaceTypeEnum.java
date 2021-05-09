package com.fjzcit.zcatp.common.constant;

import com.fjzcit.zcatp.common.constant.http.RequestMethodEnum;

// 接口类型
public enum InterfaceTypeEnum {

    HTTP(0, "HTTP"),
    SOCKET(1, "SOCKET"),
    WEBSERVICE(2, "WEBSERVICE");

    // 成员变量
    private int code;
    private String name;

    // 构造方法
    private InterfaceTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    // 普通方法
    public static String getName(int code) {
        for (InterfaceTypeEnum c : InterfaceTypeEnum.values()) {
            if (c.getCode() == code) {
                return c.name;
            }
        }
        return null;
    }

    public static InterfaceTypeEnum getEnum(int code) {
        for (InterfaceTypeEnum e : InterfaceTypeEnum.values()) {
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