package com.fjzcit.zcatp.common.constant;

public enum CaseTypeEnum {

    FUNCTIONAL (0, "FUNCTIONAL"),
    INTERFACE (1, "INTERFACE");

    // 成员变量
    private int code;
    private String name;

    // 构造方法
    private CaseTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    // 普通方法
    public static String getName(int code) {
        for (CaseTypeEnum e : CaseTypeEnum.values()) {
            if (e.getCode() == code) {
                return e.name;
            }
        }
        return null;
    }

    public static CaseTypeEnum getEnum(int code) {
        for (CaseTypeEnum e : CaseTypeEnum.values()) {
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
