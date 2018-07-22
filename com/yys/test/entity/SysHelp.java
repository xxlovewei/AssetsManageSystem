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
@TableName("SYS_HELP")
public class SysHelp extends Model<SysHelp> {

    private static final long serialVersionUID = 1L;

    @TableId("HELP_ID")
    private String helpId;
    @TableField("HELP_KEYWORD")
    private String helpKeyword;
    @TableField("HELP_TITLE")
    private String helpTitle;
    @TableField("HELP_CONTEXT")
    private String helpContext;


    public String getHelpId() {
        return helpId;
    }

    public void setHelpId(String helpId) {
        this.helpId = helpId;
    }

    public String getHelpKeyword() {
        return helpKeyword;
    }

    public void setHelpKeyword(String helpKeyword) {
        this.helpKeyword = helpKeyword;
    }

    public String getHelpTitle() {
        return helpTitle;
    }

    public void setHelpTitle(String helpTitle) {
        this.helpTitle = helpTitle;
    }

    public String getHelpContext() {
        return helpContext;
    }

    public void setHelpContext(String helpContext) {
        this.helpContext = helpContext;
    }

    @Override
    protected Serializable pkVal() {
        return this.helpId;
    }

    @Override
    public String toString() {
        return "SysHelp{" +
        ", helpId=" + helpId +
        ", helpKeyword=" + helpKeyword +
        ", helpTitle=" + helpTitle +
        ", helpContext=" + helpContext +
        "}";
    }
}
