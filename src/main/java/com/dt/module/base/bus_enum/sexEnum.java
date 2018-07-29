package com.dt.module.base.bus_enum;

import java.io.Serializable;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

public enum sexEnum implements IEnum {
    ONE("1", "男"),
    TWO("2", "女");

    private String sex;
    private String desc;

    sexEnum(final String sex, final String desc) {
        this.sex = sex;
        this.desc = desc;
    }
    

    @Override
    public Serializable getValue() {
        return this.sex;
    }

    @JsonValue
    public String getDesc(){
        return this.desc;
    }
}
