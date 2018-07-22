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
@TableName("SYS_QUD_QUX_ARCH")
public class SysQudQuxArch extends Model<SysQudQuxArch> {

    private static final long serialVersionUID = 1L;

    @TableField("ID")
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
        return null;
    }

    @Override
    public String toString() {
        return "SysQudQuxArch{" +
        ", id=" + id +
        ", mingc=" + mingc +
        ", chengsId=" + chengsId +
        ", guobm=" + guobm +
        "}";
    }
}
