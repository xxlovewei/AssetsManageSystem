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
@TableName("MN_ORADB_TBS")
public class MnOradbTbs extends Model<MnOradbTbs> {

    private static final long serialVersionUID = 1L;

    @TableField("NODE")
    private String node;
    @TableField("ORANAME")
    private String oraname;
    @TableField("TBSNAME")
    private String tbsname;
    @TableField("SUM_SPACE")
    private Double sumSpace;
    @TableField("SUM_BLOCKS")
    private Double sumBlocks;
    @TableField("USED_SPACE")
    private Double usedSpace;
    @TableField("USED_RATE")
    private Double usedRate;
    @TableField("FREE_SPACE")
    private Double freeSpace;
    @TableField("MAX_FREE_SPACE")
    private Double maxFreeSpace;
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

    public String getTbsname() {
        return tbsname;
    }

    public void setTbsname(String tbsname) {
        this.tbsname = tbsname;
    }

    public Double getSumSpace() {
        return sumSpace;
    }

    public void setSumSpace(Double sumSpace) {
        this.sumSpace = sumSpace;
    }

    public Double getSumBlocks() {
        return sumBlocks;
    }

    public void setSumBlocks(Double sumBlocks) {
        this.sumBlocks = sumBlocks;
    }

    public Double getUsedSpace() {
        return usedSpace;
    }

    public void setUsedSpace(Double usedSpace) {
        this.usedSpace = usedSpace;
    }

    public Double getUsedRate() {
        return usedRate;
    }

    public void setUsedRate(Double usedRate) {
        this.usedRate = usedRate;
    }

    public Double getFreeSpace() {
        return freeSpace;
    }

    public void setFreeSpace(Double freeSpace) {
        this.freeSpace = freeSpace;
    }

    public Double getMaxFreeSpace() {
        return maxFreeSpace;
    }

    public void setMaxFreeSpace(Double maxFreeSpace) {
        this.maxFreeSpace = maxFreeSpace;
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
        return "MnOradbTbs{" +
        ", node=" + node +
        ", oraname=" + oraname +
        ", tbsname=" + tbsname +
        ", sumSpace=" + sumSpace +
        ", sumBlocks=" + sumBlocks +
        ", usedSpace=" + usedSpace +
        ", usedRate=" + usedRate +
        ", freeSpace=" + freeSpace +
        ", maxFreeSpace=" + maxFreeSpace +
        ", producetime=" + producetime +
        ", inserttime=" + inserttime +
        "}";
    }
}
