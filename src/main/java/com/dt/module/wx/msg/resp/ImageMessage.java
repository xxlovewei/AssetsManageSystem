package com.dt.module.wx.msg.resp;

/**
 * 图片消息 (请求消息)
 * 
 * @author NCX
 *
 */
public class ImageMessage extends BaseMessage {
	// 图片链接
	private String PicUrl;

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
}
