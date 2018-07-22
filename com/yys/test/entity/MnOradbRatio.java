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
@TableName("MN_ORADB_RATIO")
public class MnOradbRatio extends Model<MnOradbRatio> {

    private static final long serialVersionUID = 1L;

    @TableField("NODE")
    private String node;
    @TableField("ORANAME")
    private String oraname;
    @TableField("RATIONAME")
    private String rationame;
    @TableField("VALUE")
    private Double value;
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

    public String getOraname() {
        return oraname;
    }

    public void setOraname(String oraname) {
        this.oraname = oraname;
    }

    public String getRationame() {
        return rationame;
    }

    public void setRationame(String rationame) {
        this.rationame = rationame;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
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
        return "MnOradbRatio{" +
        ", node=" + node +
        ", oraname=" + oraname +
        ", rationame=" + rationame +
        ", value=" + value +
        ", producetime=" + producetime +
        ", inserttime=" + inserttime +
        "}";
    }
}
