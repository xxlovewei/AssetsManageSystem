package com.dt.module.wx.service;

/**
 * @author: jinjie
 * @date: 2018年3月22日 上午9:57:11
 * @Description: TODO
 */

public class AccessTicket {
	// 获取到的凭证
	private String ticket;

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

	public long getCtime() {
		return ctime;
	}

	public void setCtime(long ctime) {
		this.ctime = ctime;
	}

	// 凭证有效时间，单位：秒
	private int expiresIn;

	private long ctime;

}
