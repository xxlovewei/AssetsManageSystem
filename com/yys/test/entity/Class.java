package com.yys.test.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
public class Class extends Model<Class> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;
    @TableField("CODE")
    private String code;
    @TableField("NAME")
    private String name;
    @TableField("DR")
    private Integer dr;
    @TableField("TS")
    private LocalDateTime ts;
    @TableField("CREATOR")
    private String creator;
    @TableField("CREATION_TIME")
    private LocalDateTime creationTime;
    @TableField("MODIFIER")
    private String modifier;
    @TableField("MODIFIED_TIME")
    private LocalDateTime modifiedTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDr() {
        return dr;
    }

    public void setDr(Integer dr) {
        this.dr = dr;
    }

    public LocalDateTime getTs() {
        return ts;
    }

    public void setTs(LocalDateTime ts) {
        this.ts = ts;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public LocalDateTime getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(LocalDateTime modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Class{" +
        ", id=" + id +
        ", code=" + code +
        ", name=" + name +
        ", dr=" + dr +
        ", ts=" + ts +
        ", creator=" + creator +
        ", creationTime=" + creationTime +
        ", modifier=" + modifier +
        ", modifiedTime=" + modifiedTime +
        "}";
    }
}
