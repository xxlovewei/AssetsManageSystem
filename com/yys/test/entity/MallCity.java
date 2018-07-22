package com.yys.test.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
@TableName("MALL_CITY")
public class MallCity extends Model<MallCity> {

    private static final long serialVersionUID = 1L;

    @TableField("AREA_ID")
    private String areaId;
    @TableField("AREA_NAME")
    private String areaName;
    @TableField("IS_USED")
    private String isUsed;


    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(String isUsed) {
        this.isUsed = isUsed;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "MallCity{" +
        ", areaId=" + areaId +
        ", areaName=" + areaName +
        ", isUsed=" + isUsed +
        "}";
    }
}
