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
@TableName("SYS_DICT")
public class SysDict extends Model<SysDict> {

    private static final long serialVersionUID = 1L;

    @TableId("DICT_ID")
    private String dictId;
    @TableField("NAME")
    private String name;
    @TableField("DICT_LEVEL")
    private String dictLevel;
    @TableField("STATUS")
    private String status;
    @TableField("DELETED")
    private String deleted;
    @TableField("MARK")
    private String mark;


    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDictLevel() {
        return dictLevel;
    }

    public void setDictLevel(String dictLevel) {
        this.dictLevel = dictLevel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    protected Serializable pkVal() {
        return this.dictId;
    }

    @Override
    public String toString() {
        return "SysDict{" +
        ", dictId=" + dictId +
        ", name=" + name +
        ", dictLevel=" + dictLevel +
        ", status=" + status +
        ", deleted=" + deleted +
        ", mark=" + mark +
        "}";
    }
}
