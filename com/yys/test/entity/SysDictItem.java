package com.yys.test.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 杨永生
 * @since 2018-07-22
 */
@TableName("SYS_DICT_ITEM")
public class SysDictItem extends Model<SysDictItem> {

    private static final long serialVersionUID = 1L;

    @TableField("DICT_ID")
    private String dictId;
    @TableId("DICT_ITEM_ID")
    private String dictItemId;
    @TableField("NAME")
    private String name;
    @TableField("SORT")
    private Double sort;
    @TableField("MARK")
    private String mark;
    @TableField("CODE")
    private String code;


    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId;
    }

    public String getDictItemId() {
        return dictItemId;
    }

    public void setDictItemId(String dictItemId) {
        this.dictItemId = dictItemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSort() {
        return sort;
    }

    public void setSort(Double sort) {
        this.sort = sort;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    protected Serializable pkVal() {
        return this.dictItemId;
    }

    @Override
    public String toString() {
        return "SysDictItem{" +
        ", dictId=" + dictId +
        ", dictItemId=" + dictItemId +
        ", name=" + name +
        ", sort=" + sort +
        ", mark=" + mark +
        ", code=" + code +
        "}";
    }
}
