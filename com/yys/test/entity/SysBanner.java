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
@TableName("SYS_BANNER")
public class SysBanner extends Model<SysBanner> {

    private static final long serialVersionUID = 1L;

    @TableId("BANNER_ID")
    private String bannerId;
    @TableField("NAME")
    private String name;
    @TableField("TYPE")
    private String type;
    @TableField("IS_USED")
    private String isUsed;
    @TableField("IS_DELETE")
    private String isDelete;


    public String getBannerId() {
        return bannerId;
    }

    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(String isUsed) {
        this.isUsed = isUsed;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    protected Serializable pkVal() {
        return this.bannerId;
    }

    @Override
    public String toString() {
        return "SysBanner{" +
        ", bannerId=" + bannerId +
        ", name=" + name +
        ", type=" + type +
        ", isUsed=" + isUsed +
        ", isDelete=" + isDelete +
        "}";
    }
}
