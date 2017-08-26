package com.dt.module.demo.entity;

 



import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;


@TableName("user")
public class User implements Serializable {

    /**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;

//	/** 用户ID */
//    private Long id;
//
//    /** 用户名 */
//    private String name;
//
//    /** 用户年龄 */
//    private Integer age;

    @TableField(exist = false)
    private String state;
}