package com.dt.module.mybatiesdemo.entity;

import com.dt.core.common.base.BaseEntity;

/** 
 * @author: algernonking
 * @date: 2018年7月22日 上午9:04:05 
 * @Description: TODO 
 */
public class DtUser extends BaseEntity{
	private String username;
    
	private String id;
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	private String password;
	
}

