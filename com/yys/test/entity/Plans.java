package com.yys.test.entity;

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
public class Plans extends Model<Plans> {

    private static final long serialVersionUID = 1L;

    @TableField("PLAN_TABLE_OUTPUT")
    private String planTableOutput;


    public String getPlanTableOutput() {
        return planTableOutput;
    }

    public void setPlanTableOutput(String planTableOutput) {
        this.planTableOutput = planTableOutput;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "Plans{" +
        ", planTableOutput=" + planTableOutput +
        "}";
    }
}
