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
public class Temp extends Model<Temp> {

    private static final long serialVersionUID = 1L;

    @TableField("NUM")
    private String num;
    @TableField("NAME")
    private String name;
    @TableField("SEX")
    private String sex;
    @TableField("CLASSES")
    private String classes;
    @TableField("COURSE_NAME")
    private String courseName;


    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "Temp{" +
        ", num=" + num +
        ", name=" + name +
        ", sex=" + sex +
        ", classes=" + classes +
        ", courseName=" + courseName +
        "}";
    }
}
