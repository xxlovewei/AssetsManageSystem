package com.dt.module.cmdb.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dt.core.common.base.BaseModel;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author algernonking
 * @since 2020-04-20
 */

@TableName("res_label_tpl")

public class ResLabelTpl extends BaseModel<ResLabelTpl> {

    private static final long serialVersionUID = 1L;

    @TableField("id")
    private String id;
    /**
     * rwm|txm
     */
    @TableField("type")
    private String type;
    /**
     * 图片位置，up｜down
     */
    @TableField("picloc")
    private String picloc;
    /**
     * 字段顺序
     */
    @TableField("ctlcols")
    private String ctlcols;
    /**
     * 是否默认选中
     */
    @TableField("ifdef")
    private String ifdef;
    @TableField("ctlcolsstr")
    private String ctlcolsstr;
    @TableField("ctlvalue")
    private String ctlvalue;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPicloc() {
        return picloc;
    }

    public void setPicloc(String picloc) {
        this.picloc = picloc;
    }

    public String getCtlcols() {
        return ctlcols;
    }

    public void setCtlcols(String ctlcols) {
        this.ctlcols = ctlcols;
    }

    public String getIfdef() {
        return ifdef;
    }

    public void setIfdef(String ifdef) {
        this.ifdef = ifdef;
    }

    public String getCtlcolsstr() {
        return ctlcolsstr;
    }

    public void setCtlcolsstr(String ctlcolsstr) {
        this.ctlcolsstr = ctlcolsstr;
    }

    public String getCtlvalue() {
        return ctlvalue;
    }

    public void setCtlvalue(String ctlvalue) {
        this.ctlvalue = ctlvalue;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "ResLabelTpl{" +
                "id=" + id +
                ", type=" + type +
                ", picloc=" + picloc +
                ", ctlcols=" + ctlcols +
                ", ifdef=" + ifdef +
                ", ctlcolsstr=" + ctlcolsstr +
                ", ctlvalue=" + ctlvalue +
                "}";
    }
}
