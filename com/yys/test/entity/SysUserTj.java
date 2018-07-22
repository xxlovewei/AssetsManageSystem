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
@TableName("SYS_USER_TJ")
public class SysUserTj extends Model<SysUserTj> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;
    @TableField("TJDAY")
    private LocalDateTime tjday;
    @TableField("USERADDS")
    private Long useradds;
    @TableField("SERVICECALL")
    private Long servicecall;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getTjday() {
        return tjday;
    }

    public void setTjday(LocalDateTime tjday) {
        this.tjday = tjday;
    }

    public Long getUseradds() {
        return useradds;
    }

    public void setUseradds(Long useradds) {
        this.useradds = useradds;
    }

    public Long getServicecall() {
        return servicecall;
    }

    public void setServicecall(Long servicecall) {
        this.servicecall = servicecall;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysUserTj{" +
        ", id=" + id +
        ", tjday=" + tjday +
        ", useradds=" + useradds +
        ", servicecall=" + servicecall +
        "}";
    }
}
