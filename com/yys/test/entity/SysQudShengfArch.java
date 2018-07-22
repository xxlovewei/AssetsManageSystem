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
@TableName("SYS_QUD_SHENGF_ARCH")
public class SysQudShengfArch extends Model<SysQudShengfArch> {

    private static final long serialVersionUID = 1L;

    @TableField("ID")
    private String id;
    @TableField("MINGC")
    private String mingc;
    @TableField("JIANC")
    private String jianc;
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

    public String getJianc() {
        return jianc;
    }

    public void setJianc(String jianc) {
        this.jianc = jianc;
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
        return "SysQudShengfArch{" +
        ", id=" + id +
        ", mingc=" + mingc +
        ", jianc=" + jianc +
        ", guobm=" + guobm +
        "}";
    }
}
