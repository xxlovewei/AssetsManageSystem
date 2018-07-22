package com.yys.test.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("SYS_FILES")
public class SysFiles extends Model<SysFiles> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;
    @TableField("PATH")
    private String path;
    @TableField("TYPE")
    private String type;
    @TableField("BUS")
    private String bus;
    @TableField("MARK")
    private String mark;
    @TableField("FILENAME")
    private String filename;
    @TableField("CDATE")
    private LocalDateTime cdate;
    @TableField("COL_A")
    private String colA;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public LocalDateTime getCdate() {
        return cdate;
    }

    public void setCdate(LocalDateTime cdate) {
        this.cdate = cdate;
    }

    public String getColA() {
        return colA;
    }

    public void setColA(String colA) {
        this.colA = colA;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysFiles{" +
        ", id=" + id +
        ", path=" + path +
        ", type=" + type +
        ", bus=" + bus +
        ", mark=" + mark +
        ", filename=" + filename +
        ", cdate=" + cdate +
        ", colA=" + colA +
        "}";
    }
}
