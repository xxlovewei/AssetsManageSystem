package com.dt.module.wx.service;

import org.springframework.stereotype.Service;

import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.sql.Insert;
import com.dt.core.dao.sql.Update;
import com.dt.core.dao.util.TypedHashMap;

/**
 * @author: jinjie
 * @date: 2018年5月7日 下午2:46:28
 * @Description: TODO
 */
@Service
public class MessageService extends BaseService {

	public R deleteMessage(String id) {
		Update me = new Update("wx_msg_def");
		me.setIf("dr", 1);
		me.where().and("id=?", id);
		db.execute(me);
		return R.SUCCESS_OPER();
	}

	public R updateMessage(TypedHashMap<String, Object> ps) {
		Update me = new Update("wx_msg_def");
		me.setIf("mark", ps.getString("mark", ""));
		me.setIf("code", ps.getString("code", ""));
		me.setIf("name", ps.getString("name", ""));
		me.setIf("msgtype", ps.getString("msgtype", ""));
		me.setIf("value", ps.getString("value", ""));
		me.where().and("id=?", ps.getString("id"));
		db.execute(me);
		return R.SUCCESS_OPER();
	}

	public R addMessage(TypedHashMap<String, Object> ps) {
		Insert me = new Insert("wx_msg_def");
		me.set("dr", 0);
		me.set("id", db.getUUID());
		me.set("group_id", db.getUUID());
		me.setIf("mark", ps.getString("mark", ""));
		me.setIf("code", ps.getString("code", ""));
		me.setIf("name", ps.getString("name", ""));
		me.setIf("msgtype", ps.getString("msgtype", ""));
		me.setIf("value", ps.getString("value", ""));
		db.execute(me);
		return R.SUCCESS_OPER();
	}

	public R queryMessageById(String id) {
		return R.SUCCESS_OPER(db.uniqueRecord("select * from wx_msg_def where id=?", id).toJsonObject());
	}

	public R queryMessages() {
		return R.SUCCESS_OPER(
				db.query("select * from wx_msg_def where dr=0 order by msgtype").toJsonArrayWithJsonObject());
	}

	// 图文
	public R queryImageTextMessagesGroup() {
		return R.SUCCESS_OPER(
				db.query("select * from wx_msg_def where dr=0 and msgtype='6'").toJsonArrayWithJsonObject());
	}

	public R queryImageTextMessages(String group_id) {
		return R.SUCCESS_OPER(db.query("select * from wx_msg_imgitem where group_id=? and  dr=0 order by rn", group_id)
				.toJsonArrayWithJsonObject());
	}

	public R deleteImageTextMessage(String id) {
		Update me = new Update("wx_msg_imgitem");
		me.setIf("dr", 1);
		me.where().and("id=?", id);
		db.execute(me);
		return R.SUCCESS_OPER();
	}

	public R updateImageTextMessage(TypedHashMap<String, Object> ps) {
		Update me = new Update("wx_msg_imgitem");
		me.setIf("title", ps.getString("mark", ""));
		me.setIf("msgdesc", ps.getString("code", ""));
		me.setIf("docurl", ps.getString("name", ""));
		me.setIf("imgurl", ps.getString("msgtype", ""));
		me.setIf("rn", ps.getString("rn", ""));
		me.setIf("mark", ps.getString("mark", ""));
		me.where().and("id=?", ps.getString("id"));
		db.execute(me);
		return R.SUCCESS_OPER();
	}

	public R addImageTextMessage(TypedHashMap<String, Object> ps) {
		Insert me = new Insert("wx_msg_imgitem");
		me.set("dr", 0);
		me.set("id", db.getUUID());
		me.setIf("title", ps.getString("mark", ""));
		me.setIf("msgdesc", ps.getString("code", ""));
		me.setIf("docurl", ps.getString("name", ""));
		me.setIf("imgurl", ps.getString("msgtype", ""));
		me.setIf("group_id", ps.getString("group_id", ""));
		me.setIf("rn", ps.getString("rn", ""));
		me.setIf("mark", ps.getString("mark", ""));
		db.execute(me);
		return R.SUCCESS_OPER();
	}

	public R queryImageTextMessageById(String id) {
		return R.SUCCESS_OPER(db.uniqueRecord("select * from wx_msg_imgitem where id=?", id).toJsonObject());
	}

}
