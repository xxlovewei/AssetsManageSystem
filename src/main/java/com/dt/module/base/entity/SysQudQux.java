package com.dt.module.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dt.core.common.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
/**
 * <p>
 * 
 * </p>
 *
 * @author algernonking
 * @since 2018-07-27
 */
@TableName("SYS_QUD_QUX")
public class SysQudQux extends BaseModel<SysQudQux> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;
    @TableField("MINGC")
    private String mingc;
    @TableField("CHENGS_ID")
    private String chengsId;
    @TableField("GUOBM")
    private String guobm;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMingc() {
        return mingc;
    }

    public void setMingc(String mingc) {
        this.mingc = mingc;
    }

    public String getChengsId() {
        return chengsId;
    }

    public void setChengsId(String chengsId) {
        this.chengsId = chengsId;
    }

    public String getGuobm() {
        return guobm;
    }

    public void setGuobm(String guobm) {
        this.guobm = guobm;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysQudQux{" +
        ", id=" + id +
        ", mingc=" + mingc +
        ", chengsId=" + chengsId +
        ", guobm=" + guobm +
        "}";
    }
}
