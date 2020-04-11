package com.dt.module.flow.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dt.core.common.base.BaseModel;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * <p>
 * 
 * </p>
 *
 * @author algernonking
 * @since 2020-04-11
 */
 
@TableName("sys_process_setting")
 
public class SysProcessSetting extends BaseModel<SysProcessSetting> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;
    /**
     * 流程名称
     */
    @TableField("name")
    private String name;
    /**
     * 流程编码
     */
    @TableField("code")
    private String code;
    /**
     * 流程定义ID
     */
    @TableField("processdefid")
    private String processdefid;
    /**
     * 备注
     */
    @TableField("mark")
    private String mark;
    /**
     * 流程类型 system|user
     */
    @TableField("type")
    private String type;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProcessdefid() {
        return processdefid;
    }

    public void setProcessdefid(String processdefid) {
        this.processdefid = processdefid;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysProcessSetting{" +
        "id=" + id +
        ", name=" + name +
        ", code=" + code +
        ", processdefid=" + processdefid +
        ", mark=" + mark +
        ", type=" + type +
        "}";
    }
}
