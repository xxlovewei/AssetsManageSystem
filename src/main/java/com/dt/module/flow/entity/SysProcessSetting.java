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
 * @since 2020-04-04
 */
 
@TableName("sys_process_setting")
 
public class SysProcessSetting extends BaseModel<SysProcessSetting> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;
    @TableField("name")
    private String name;
    @TableField("code")
    private String code;
    @TableField("processdefid")
    private String processdefid;


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
        "}";
    }
}
