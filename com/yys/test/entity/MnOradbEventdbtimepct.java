package com.yys.test.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
@TableName("MN_ORADB_EVENTDBTIMEPCT")
public class MnOradbEventdbtimepct extends Model<MnOradbEventdbtimepct> {

    private static final long serialVersionUID = 1L;

    @TableField("NODE")
    private String node;
    @TableField("INSTANCE")
    private String instance;
    @TableField("DBTIMEPCT")
    private Double dbtimepct;
    @TableField("DBRECTIME")
    private LocalDateTime dbrectime;
    @TableField("PRODUCETIME")
    private String producetime;
    @TableField("INSERTTIME")
    private LocalDateTime inserttime;


    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    public Double getDbtimepct() {
        return dbtimepct;
    }

    public void setDbtimepct(Double dbtimepct) {
        this.dbtimepct = dbtimepct;
    }

    public LocalDateTime getDbrectime() {
        return dbrectime;
    }

    public void setDbrectime(LocalDateTime dbrectime) {
        this.dbrectime = dbrectime;
    }

    public String getProducetime() {
        return producetime;
    }

    public void setProducetime(String producetime) {
        this.producetime = producetime;
    }

    public LocalDateTime getInserttime() {
        return inserttime;
    }

    public void setInserttime(LocalDateTime inserttime) {
        this.inserttime = inserttime;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "MnOradbEventdbtimepct{" +
        ", node=" + node +
        ", instance=" + instance +
        ", dbtimepct=" + dbtimepct +
        ", dbrectime=" + dbrectime +
        ", producetime=" + producetime +
        ", inserttime=" + inserttime +
        "}";
    }
}
