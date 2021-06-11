package com.alphabethub.enumeration;

/**
 * 自定义性别枚举
 */
public enum Gender {
    MEN(1, "男"),
    WOMEN(0, "女");

    private int value;
    private String desc;

    Gender(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
