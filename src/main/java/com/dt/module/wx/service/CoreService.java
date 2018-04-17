/**
 * 
 */
package com.dt.module.wx.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.dt.module.wx.msg.resp.Article;
import com.dt.module.wx.msg.resp.NewsMessage;
import com.dt.module.wx.msg.resp.TextMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import com.dt.core.common.base.BaseService;
import com.dt.core.dao.Rcd;
import com.dt.core.dao.RcdSet;
import com.dt.module.wx.util.MessageUtil;

/**
 * 
 */
@Service
@Configuration
@PropertySource(value = "classpath:config.properties", encoding = "UTF-8")
public class CoreService extends BaseService {

	@Value("${wx.weburl}")
	public String weburl;

	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return xml
	 */
	public String processRequest(HttpServletRequest request) {
		// xml格式的消息数据
		String respXml = null;
		// 默认返回的文本消息内容
		String respContent = "";
		try {
			// 调用parseXml方法解析请求消息
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// 发送方帐号
			String fromUserName = requestMap.get("FromUserName");
			// 开发者微信号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");

			// 回复文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

			HttpSession session = request.getSession(true);

			if (null == session.getAttribute("user")) {
				Map userMap = new HashMap();
				userMap.put("OPENID", fromUserName);
				session.setAttribute("user", userMap);
			} else {
				Map userMap = (Map) session.getAttribute("user");
				userMap.put("OPENID", fromUserName);
			}

			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				respContent = "您发送的是文本消息！";

				respContent = eventbykey(requestMap.get("Content"), fromUserName, toUserName);

				return respContent;

			}
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "您发送的是图片消息！";
			}
			// 语音消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "您发送的是语音消息！";
			}
			// 视频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
				respContent = "您发送的是视频消息！";
			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "您发送的是地理位置消息！";
			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "您发送的是链接消息！";
			}
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 关注
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = "";

					return respContent;

				}
				// 取消关注
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户不会再收到公众账号发送的消息，因此不需要回复
				}
				// 扫描带参数二维码
				else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
					// TODO 处理扫描带参数二维码事件

					respContent = "";

					return respContent;

				}
				// 上报地理位置
				else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {
					// TODO 处理上报地理位置事件
				}
				// 自定义菜单
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// TODO 处理菜单点击事件
					respContent = eventbykey(requestMap.get("EventKey"), fromUserName, toUserName);

					return respContent;
				}
			}

			System.out.println("RETURN" + respContent);
			// 设置文本消息的内容
			textMessage.setContent(respContent);
			// 将文本消息对象转换成xml
			respXml = MessageUtil.textMessageToXml(textMessage);

			System.out.println(respXml);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return respXml;
	}

	public String eventbykey(String key, String fromUserName, String toUserName) {

		Rcd keyrcd = db.uniqueRecord("SELECT * FROM MAYOR_MP_KEY WHERE (KEYCODE = ? OR KEYNAME=?)", key, key);

		if ("6".equals(keyrcd.getString("KEYTYPE"))) {
			// 为图文消息
			Rcd grouprcd = db.uniqueRecord(
					"SELECT * FROM MAYOR_MP_IMAGE_GROUP WHERE ID IN (SELECT KEYVAULE FROM MAYOR_MP_KEY WHERE KEYCODE = ? OR KEYNAME=?)",
					key, key);

			System.out.println("VVVVVVVV" + grouprcd.getString("ID") + "VVVVVVVVV");

			RcdSet set = db.query(
					"SELECT t.*,mi.ORDERNUM FROM MAYOR_MP_IMAGE_TEXT t JOIN MAYOR_MP_IMAGE_GROUP_ITEMS mi ON t.ID = mi.IMAGEID WHERE GROUPID=? ORDER BY mi.ORDERNUM ASC",
					grouprcd.getString("ID"));

			NewsMessage newsMessage = new NewsMessage();
			newsMessage.setArticleCount(set.size());
			newsMessage.setToUserName(fromUserName);
			newsMessage.setFromUserName(toUserName);
			newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
			newsMessage.setCreateTime(new Date().getTime());

			List list = new ArrayList();
			for (int i = 0; i < set.size(); i++) {
				Article art = new Article();
				art.setTitle(set.getRcd(i).getString("TITLE"));
				art.setPicUrl(
						set.getRcd(i).getString("FILENAME").startsWith("http") ? set.getRcd(i).getString("FILENAME")
								: weburl + "/viewImage.do?fn=" + set.getRcd(i).getString("FILENAME"));
				art.setUrl(set.getRcd(i).getString("URL"));
				art.setDescription(set.getRcd(i).getString("DESCRIPTION"));
				list.add(art);
			}

			newsMessage.setArticles(list);

			return MessageUtil.newsMessageToXml(newsMessage);
		} else if ("1".equals(keyrcd.getString("KEYTYPE"))) {

			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setContent(keyrcd.getString("KEYVAULE"));

			return MessageUtil.textMessageToXml(textMessage);
		}

		return null;

	}

	public static void main(String[] args) {

	}
}