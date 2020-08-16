package com.dt.module.zc.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dt.core.common.base.BaseModel;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author algernonking
 * @since 2020-04-25
 */

@TableName("res_allocate_item")

public class ResAllocateItem extends BaseModel<ResAllocateItem> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;
    @TableField("allocateid")
    private String allocateid;
    @TableField("residprerecycle")
    private String residprerecycle;
    @TableField("resid")
    private String resid;
    @TableField("touseduserid")
    private String touseduserid;
    @TableField("tousedusername")
    private String tousedusername;
    @TableField("tousedpartid")
    private String tousedpartid;
    @TableField("tousedpartname")
    private String tousedpartname;
    @TableField("tousedcompid")
    private String tousedcompid;
    @TableField("tousedcompname")
    private String tousedcompname;
    @TableField("tobelongcompid")
    private String tobelongcompid;
    @TableField("tobelongcompname")
    private String tobelongcompname;
    @TableField("tobelongpartid")
    private String tobelongpartid;
    @TableField("tobelongpartname")
    private String tobelongpartname;
    @TableField("toloc")
    private String toloc;
    @TableField("tolocname")
    private String tolocname;
    @TableField("tolocdtl")
    private String tolocdtl;
    @TableField("status")
    private String status;
    @TableField("busuuid")
    private String busuuid;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAllocateid() {
        return allocateid;
    }

    public void setAllocateid(String allocateid) {
        this.allocateid = allocateid;
    }

    public String getResidprerecycle() {
        return residprerecycle;
    }

    public void setResidprerecycle(String residprerecycle) {
        this.residprerecycle = residprerecycle;
    }

    public String getResid() {
        return resid;
    }

    public void setResid(String resid) {
        this.resid = resid;
    }

    public String getTouseduserid() {
        return touseduserid;
    }

    public void setTouseduserid(String touseduserid) {
        this.touseduserid = touseduserid;
    }

    public String getTousedusername() {
        return tousedusername;
    }

    public void setTousedusername(String tousedusername) {
        this.tousedusername = tousedusername;
    }

    public String getTousedpartid() {
        return tousedpartid;
    }

    public void setTousedpartid(String tousedpartid) {
        this.tousedpartid = tousedpartid;
    }

    public String getTousedpartname() {
        return tousedpartname;
    }

    public void setTousedpartname(String tousedpartname) {
        this.tousedpartname = tousedpartname;
    }

    public String getTousedcompid() {
        return tousedcompid;
    }

    public void setTousedcompid(String tousedcompid) {
        this.tousedcompid = tousedcompid;
    }

    public String getTousedcompname() {
        return tousedcompname;
    }

    public void setTousedcompname(String tousedcompname) {
        this.tousedcompname = tousedcompname;
    }

    public String getTobelongcompid() {
        return tobelongcompid;
    }

    public void setTobelongcompid(String tobelongcompid) {
        this.tobelongcompid = tobelongcompid;
    }

    public String getTobelongcompname() {
        return tobelongcompname;
    }

    public void setTobelongcompname(String tobelongcompname) {
        this.tobelongcompname = tobelongcompname;
    }

    public String getTobelongpartid() {
        return tobelongpartid;
    }

    public void setTobelongpartid(String tobelongpartid) {
        this.tobelongpartid = tobelongpartid;
    }

    public String getTobelongpartname() {
        return tobelongpartname;
    }

    public void setTobelongpartname(String tobelongpartname) {
        this.tobelongpartname = tobelongpartname;
    }

    public String getToloc() {
        return toloc;
    }

    public void setToloc(String toloc) {
        this.toloc = toloc;
    }

    public String getTolocname() {
        return tolocname;
    }

    public void setTolocname(String tolocname) {
        this.tolocname = tolocname;
    }

    public String getTolocdtl() {
        return tolocdtl;
    }

    public void setTolocdtl(String tolocdtl) {
        this.tolocdtl = tolocdtl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBusuuid() {
        return busuuid;
    }

    public void setBusuuid(String busuuid) {
        this.busuuid = busuuid;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ResAllocateItem{" +
                "id=" + id +
                ", allocateid=" + allocateid +
                ", residprerecycle=" + residprerecycle +
                ", resid=" + resid +
                ", touseduserid=" + touseduserid +
                ", tousedusername=" + tousedusername +
                ", tousedpartid=" + tousedpartid +
                ", tousedpartname=" + tousedpartname +
                ", tousedcompid=" + tousedcompid +
                ", tousedcompname=" + tousedcompname +
                ", tobelongcompid=" + tobelongcompid +
                ", tobelongcompname=" + tobelongcompname +
                ", tobelongpartid=" + tobelongpartid +
                ", tobelongpartname=" + tobelongpartname +
                ", toloc=" + toloc +
                ", tolocname=" + tolocname +
                ", tolocdtl=" + tolocdtl +
                ", status=" + status +
                ", busuuid=" + busuuid +
                "}";
    }
}
