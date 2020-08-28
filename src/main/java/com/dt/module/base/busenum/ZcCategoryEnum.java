package com.dt.module.base.busenum;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

public enum ZcCategoryEnum implements IEnum<Serializable> {

    CATEGORY_ZC("3", "资产"),
    CATEGORY_HC("7", "耗材"),
    CATEGORY_BJ("8", "备件");

    private String code;
    private String name;

    ZcCategoryEnum(final String code, final String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public String getValue() {

        return this.code;
    }

    @JsonValue
    public String getName() {
        return this.name;
    }


    @JsonValue
    public static String parseCode(String code) {
        for (ZcCategoryEnum e : ZcCategoryEnum.values()) {
            if (e.getValue().equals(code)) {
                return e.name;
            }
        }
        return "";
    }
}
