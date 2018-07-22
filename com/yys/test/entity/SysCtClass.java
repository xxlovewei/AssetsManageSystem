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
@TableName("SYS_CT_CLASS")
public class SysCtClass extends Model<SysCtClass> {

    private static final long serialVersionUID = 1L;

    @TableId("CLASS_ID")
    private String classId;
    @TableField("TYPE")
    private String type;
    @TableField("NAME")
    private String name;
    @TableField("PIC_ID")
    private String picId;
    @TableField("IS_USED")
    private String isUsed;
    @TableField("IS_DELETE")
    private String isDelete;
    @TableField("OD")
    private Double od;
    @TableField("MARK")
    private String mark;
    @TableField("MODULE")
    private String module;
    @TableField("STATUS")
    private String status;
    @TableField("COL_A")
    private String colA;
    @TableField("COL_B")
    private String colB;
    @TableField("COL_C")
    private String colC;
    @TableField("COL_D")
    private String colD;
    @TableField("COL_E")
    private String colE;


    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicId() {
        return picId;
    }

    public void setPicId(String picId) {
        this.picId = picId;
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

    public Double getOd() {
        return od;
    }

    public void setOd(Double od) {
        this.od = od;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getColA() {
        return colA;
    }

    public void setColA(String colA) {
        this.colA = colA;
    }

    public String getColB() {
        return colB;
    }

    public void setColB(String colB) {
        this.colB = colB;
    }

    public String getColC() {
        return colC;
    }

    public void setColC(String colC) {
        this.colC = colC;
    }

    public String getColD() {
        return colD;
    }

    public void setColD(String colD) {
        this.colD = colD;
    }

    public String getColE() {
        return colE;
    }

    public void setColE(String colE) {
        this.colE = colE;
    }

    @Override
    protected Serializable pkVal() {
        return this.classId;
    }

    @Override
    public String toString() {
        return "SysCtClass{" +
        ", classId=" + classId +
        ", type=" + type +
        ", name=" + name +
        ", picId=" + picId +
        ", isUsed=" + isUsed +
        ", isDelete=" + isDelete +
        ", od=" + od +
        ", mark=" + mark +
        ", module=" + module +
        ", status=" + status +
        ", colA=" + colA +
        ", colB=" + colB +
        ", colC=" + colC +
        ", colD=" + colD +
        ", colE=" + colE +
        "}";
    }
}
